package com.pwr.sharebook.attachments

import java.lang.RuntimeException

class CannotFindAttachmentException(val attachmentId: String) : RuntimeException("Cannot find attachment with id $attachmentId") {

}
