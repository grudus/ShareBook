package com.pwr.sharebook.attachments

import com.google.api.services.drive.Drive
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import com.google.api.services.drive.model.FileList



@Component
class GoogleDriveAttachmentRepository
@Autowired
constructor(private val googleDrive: Drive): AttachmentRepository {

    override fun getAll() {
        val result: FileList = googleDrive.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute()

        val files: List<com.google.api.services.drive.model.File> = result.files

        println("GET FILES")
        files.forEach { println(it) }
    }

    override fun upload(file: File): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun download(path: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
