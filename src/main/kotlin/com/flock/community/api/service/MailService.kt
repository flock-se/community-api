package com.flock.community.api.service

import community.flock.eco.core.services.MailService
import community.flock.eco.feature.user.model.User
import org.springframework.stereotype.Service
import java.security.Principal
import java.util.*
import org.springframework.security.oauth2.provider.OAuth2Authentication
import java.io.UnsupportedEncodingException
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
open class MailServiceImpl : MailService {

    val properties: Properties = Properties();
    val session : Session = Session.getDefaultInstance(properties, null)

    override fun sendMail(principal: Principal) {
        val auth = principal as OAuth2Authentication
        val user = auth.principal as User

        try {
            val msg: Message = MimeMessage(session)
            msg.setFrom(InternetAddress("info@bring-the-elephant-home.appspotmail.com", "Bring the elephants home"))
            msg.addRecipient(Message.RecipientType.TO, InternetAddress(user.email, user.name))
            msg.setSubject("This is a test email")
            msg.setText("This is a test")
            Transport.send(msg)
        } catch (e: AddressException) {
            println(e)
        } catch (e: MessagingException) {
            println(e)
        } catch (e: UnsupportedEncodingException) {
            println(e)
        }

    }
}