package com.pwr.sharebook

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

    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun bindExceptionException(e: BindException): ErrorResponse =
            ErrorResponse("An error occurred while parsing", toCodes(e.bindingResult))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun bindExceptionMethodArgumentNotValidException(e: MethodArgumentNotValidException): ErrorResponse =
            ErrorResponse("An error occurred while parsing", toCodes(e.bindingResult))

    private fun toCodes(b: BindingResult): List<String> =
            b.allErrors.map { it.code!! }
}
