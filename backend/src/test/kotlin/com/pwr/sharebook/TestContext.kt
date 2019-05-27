package com.pwr.sharebook



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
import com.pwr.sharebook.attachments.AttachmentIoService
import com.pwr.sharebook.attachments.MockAttachmentIoService
import com.pwr.sharebook.spring.ShareBookContext
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.*
import org.springframework.core.io.ResourceLoader
import java.io.InputStreamReader
import javax.sql.DataSource

@Configuration
@Import(ShareBookContext::class)
@ComponentScan("com.pwr.sharebook")
class TestContext {

    @Bean
    @Primary
    fun primaryDataSource(@Value("\${spring.datasource.driver-class-name}") driver: String,
                          @Value("\${spring.datasource.url}") url: String,
                          @Value("\${spring.datasource.username}") username: String,
                          @Value("\${spring.datasource.password}") password: String): DataSource =

            DataSourceBuilder.create()
                    .username(username)
                    .password(password)
                    .url(url)
                    .driverClassName(driver)
                    .build()

    @Bean
    @Primary
    fun mockAttachmentIoService(): AttachmentIoService<String> =
            MockAttachmentIoService()

    @Bean
    fun googleMockCredentials(): Credential =
            Mockito.mock(Credential::class.java)


    @Bean("googleJsonFactory")
    fun googleJsonFactory(): JsonFactory =
            JacksonFactory.getDefaultInstance()

    @Bean("googleNetHttpTransport")
    fun googleNetHttpTransport(): NetHttpTransport =
            GoogleNetHttpTransport.newTrustedTransport()

    @Bean
    fun googleDrive(): Drive = Mockito.mock(Drive::class.java)


}
