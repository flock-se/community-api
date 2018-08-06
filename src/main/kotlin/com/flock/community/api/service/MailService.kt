package com.flock.community.api.service

import com.flock.community.api.model.User
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException
import java.security.Principal
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
open class MailService {
    val properties: Properties = Properties();
    val session = Session.getDefaultInstance(properties, null)


    fun sendMail(principal: Principal) {

        val user: User = (principal as OAuth2Authentication).principal as User;

        try {
            val msg: Message = MimeMessage(session)
            msg.setFrom(InternetAddress(user.email, user.name))
            msg.addRecipient(Message.RecipientType.TO, InternetAddress(user.email, user.name))
            msg.setSubject("TEST")
            msg.setText("This is a test")
            Transport.send(msg);
        } catch (e: AddressException) {
            println(e)
        } catch (e: MessagingException) {
            println(e)
        } catch (e: UnsupportedEncodingException) {
            println(e)
        } finally {

        }
    }
}