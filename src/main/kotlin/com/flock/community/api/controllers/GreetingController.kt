package com.flock.community.api.controllers

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

@RestController
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
                "Hello, $name, from a SpringBoot Application written in Kotlin, running on Google App Engine Java8 Standard..."

    @GetMapping("/itsme")
    fun itsme(principal: Principal) = principal


    @GetMapping("/itscontext")
    fun itscontext(): SecurityContext {
        System.out.println("---dd---------------")
        return SecurityContextHolder.getContext()
    }

    @GetMapping("/itssession")
    fun itssession(request:HttpServletRequest): String {

        val session = request.getSession(true)


        if(session.getAttribute("xxx") == null){
            System.out.println("---set---------------")
            session.setAttribute("xxx", "123123")
        }

        return session.getAttribute("xxx").toString()
    }

}
