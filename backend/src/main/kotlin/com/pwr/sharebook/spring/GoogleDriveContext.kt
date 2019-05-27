package com.pwr.sharebook.spring

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.io.ResourceLoader
import java.io.File
import java.io.InputStreamReader

@Configuration
@Profile("!test")
class GoogleDriveContext {

    @Bean("googleJsonFactory")
    fun googleJsonFactory(): JsonFactory {
        return JacksonFactory.getDefaultInstance()
    }

    @Bean("googleNetHttpTransport")
    fun googleNetHttpTransport(): NetHttpTransport =
            GoogleNetHttpTransport.newTrustedTransport()

    @Bean("googleCredentials")
    fun googleCredentials(@Value("\${sharebook.attachment.transporter.google-drive.credentials}") credentialsPath: String,
                          @Value("\${sharebook.attachment.transporter.google-drive.user}") user: String,
                          @Value("\${sharebook.attachment.transporter.google-drive.data-store-path}") dataStoragePath: String,
                          resourceLoader: ResourceLoader,
                          googleNetHttpTransport: NetHttpTransport,
                          googleJsonFactory: JsonFactory): Credential {
        val inputStream = resourceLoader.getResource(credentialsPath).inputStream

        val secrets: GoogleClientSecrets = GoogleClientSecrets.load(googleJsonFactory, InputStreamReader(inputStream))

        val googleCodeFlow = GoogleAuthorizationCodeFlow.Builder(
                googleNetHttpTransport,
                googleJsonFactory,
                secrets,
                listOf(DriveScopes.DRIVE)
        )
                .setDataStoreFactory(FileDataStoreFactory(resourceLoader.getResource(dataStoragePath).file))
                .setAccessType("offline")
                .build()

        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(googleCodeFlow, receiver).authorize(user)
    }


    @Bean
    fun googleDrive(googleCredential: Credential,
                    googleNetHttpTransport: NetHttpTransport,
                    googleJsonFactory: JsonFactory,
                    @Value("\${sharebook.attachment.transporter.google-drive.application-name}") applicationName: String
    ): Drive =
            Drive.Builder(googleNetHttpTransport,
                    googleJsonFactory, googleCredential)
                    .setApplicationName(applicationName)
                    .build()

}
