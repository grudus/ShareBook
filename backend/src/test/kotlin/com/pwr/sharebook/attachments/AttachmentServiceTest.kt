package com.pwr.sharebook.attachments

import com.pwr.sharebook.AbstractDatabaseTest
import com.pwr.sharebook.group.CreateGroupRequest
import com.pwr.sharebook.group.GroupService
import com.pwr.sharebook.group.post.AddPostRequest
import com.pwr.sharebook.group.post.GroupPostService
import com.pwr.sharebook.user.AuthenticatedUser
import com.pwr.sharebook.utils.RandomUtils.randomEmail
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockMultipartFile
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AttachmentServiceTest: AbstractDatabaseTest() {

    @Autowired
    private lateinit var attachmentService: AttachmentService

    @Autowired
    private lateinit var postService: GroupPostService

    @Autowired
    private lateinit var groupService: GroupService

    @Autowired
    private lateinit var attachmentIoService: AttachmentIoService<String>


    private var postId: Long? = null
    private var groupId: Long? = null
    private var userId: Long? = null

    @Before
    fun init() {
        userId = addUser(randomEmail()).id!!
        groupId = groupService.create(CreateGroupRequest(randomText()), userId!!)
        postId = postService.addPost(AddPostRequest(randomText()), groupId!!, userId!!)
    }

    @Test
    fun shouldUploadAttachment() {
        val id = attachmentService.addAttachmentToPost(randomFile(randomText()), groupId!!, postId!!, AuthenticatedUser(userId!!, ""))

        val containsFile = (attachmentIoService as MockAttachmentIoService).containsFile(id)

        assertTrue(containsFile)
    }

    @Test
    fun shouldSaveAttachmentAndReturnId() {
        val originalFilename = randomText()
        val id = attachmentService.addAttachmentToPost(randomFile(originalFilename), groupId!!, postId!!, AuthenticatedUser(userId!!, ""))
        flush()

        val filename = attachmentService.findFilenameByLocation(id)

        assertEquals(originalFilename, filename)
    }

    private fun randomFile(originalFilename: String) =
            MockMultipartFile(originalFilename, originalFilename, "application/pdf", byteArrayOf())


}
