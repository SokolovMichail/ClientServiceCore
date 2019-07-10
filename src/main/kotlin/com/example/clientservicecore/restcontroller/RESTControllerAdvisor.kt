package com.example.clientservicecore.restcontroller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
internal class EmployeeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ClientNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun employeeNotFoundHandler(ex: ClientNotFoundException): String {
        return ex.message.toString()
    }
}