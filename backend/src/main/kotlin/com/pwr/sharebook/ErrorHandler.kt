package com.pwr.sharebook

import com.pwr.sharebook.attachments.CannotFindAttachmentException
import com.pwr.sharebook.common.RestKeys
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
@ResponseBody
class ErrorHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun bindExceptionException(e: BindException): ErrorResponse =
            ErrorResponse("An error occurred while parsing", toCodes(e.bindingResult))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun bindExceptionMethodArgumentNotValidException(e: MethodArgumentNotValidException): ErrorResponse =
            ErrorResponse("An error occurred while parsing", toCodes(e.bindingResult))

    @ExceptionHandler(CannotFindAttachmentException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun cannotFindAttachment(e: CannotFindAttachmentException): ErrorResponse {
        logger.info(e.message!!)
        return ErrorResponse(e.message, listOf(RestKeys.CANNOT_FIND_ATTACHMENT))
    }

    private fun toCodes(b: BindingResult): List<String> =
            b.allErrors.map { it.code!! }
}
