package com.pwr.sharebook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SharebookApplication

fun main(args: Array<String>) {
    runApplication<SharebookApplication>(*args)
}
