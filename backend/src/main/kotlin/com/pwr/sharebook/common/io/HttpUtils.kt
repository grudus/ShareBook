package com.pwr.sharebook.common.io

import javax.servlet.http.HttpServletResponse

object HttpUtils {

    fun addFilenameToResponse(filename: String, response: HttpServletResponse): HttpServletResponse {
        response.setHeader("Content-disposition", "attachment; filename=$filename")
        return response
    }
 }
