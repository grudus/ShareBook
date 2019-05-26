package com.pwr.sharebook.attachments

import com.google.api.client.http.InputStreamContent
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.File
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.OutputStream


@Component
class GoogleDriveAttachmentIoService
@Autowired
constructor(private val googleDrive: Drive) : AttachmentIoService<String> {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun upload(multiPartFile: MultipartFile, fileName: String): String {
        val file = File()
        file.name = fileName

        logger.info("Uploading ${multiPartFile.originalFilename} file as $fileName")

        return googleDrive.files().create(file, InputStreamContent(multiPartFile.contentType, multiPartFile.inputStream))
                .setFields("id")
                .execute()
                .id
    }

    override fun downloadById(id: String, outputStream: OutputStream) {
        logger.info("Downloading attachment with id $id")
        googleDrive.files()
                .get(id)
                .executeMediaAndDownloadTo(outputStream)
    }
}
