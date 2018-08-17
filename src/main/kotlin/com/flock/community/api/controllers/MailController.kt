package com.flock.community.api.controllers

import com.flock.community.api.service.MailServiceImpl
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class MailController {

    @GetMapping("/api/sendmail")
    fun sendMail(mailService: MailServiceImpl, principal: Principal) : Any {
        mailService.sendMail(principal)
        return "sendMail"
    }


}
