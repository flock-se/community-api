package com.flock.community.api.service

import com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URLEncoder
import java.security.MessageDigest
import java.util.*
import java.util.Base64.getEncoder
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Service
open class BuckarooService {

    @Value("\${buckaroo.websiteKey}")
    private var websiteKey: String = ""

    @Value("\${buckaroo.secretKey}")
    private var secretKey: String = ""

    fun getEncodedContent(content: String): String {
        val md = MessageDigest.getInstance("MD5")
        val md5 = md.digest(content.toByteArray())
        val base64 = getEncoder().encodeToString(md5)
        return base64
    }

    fun getHash(
        websiteKey: String,
        secretKey: String,
        httpMethod: String,
        nonce: String,
        timeStamp: String,
        requestUri: String,
        content: String
    ): String? {

        val encodedContent = getEncodedContent(content)
        val rawData = websiteKey + httpMethod + requestUri + timeStamp + nonce + encodedContent

        val sha256HMAC = Mac.getInstance("HmacSHA256")
        val secretkey = SecretKeySpec(secretKey.toByteArray(), "HmacSHA256")
        sha256HMAC.init(secretkey)
        return getEncoder().encodeToString(sha256HMAC.doFinal(rawData.toByteArray()))

    }

    fun getTimeStamp(): String {
        return (Date().getTime() / 1000).toString()
    }

    fun getNonce(): String {
        return RandomStringUtils.randomAlphanumeric(32)
    }

    fun getContent(amount: Double, description: String, issuer: String): String {
        return """{
            "Currency": "EUR",
            "AmountDebit": $amount,
            "Invoice": "$description",
            "ClientIP": {
            "Type": 0,
            "Address": "0.0.0.0"
        },
            "Services": {
            "ServiceList": [
            {
                "Name": "ideal",
                "Action": "Pay",
                "Parameters": [
                {
                    "Name": "issuer",
                    "Value": "$issuer"
                }
                ]
            }
            ]
        }
        """
    }

    fun authHeader(
        requestUri: String,
        content: String,
        httpMethod: String
    ): String {
        val nonce = getNonce()
        val timeStamp = getTimeStamp()
        var url = URLEncoder.encode(requestUri, "UTF-8").toLowerCase()
        return "hmac " + websiteKey + ":" + getHash(
            websiteKey,
            secretKey,
            httpMethod,
            nonce,
            timeStamp,
            url,
            content
        ) + ":" + nonce + ":" + timeStamp;
    }

    fun createTransaction(amount: Double, description: String, issuer: String): String {
        val requestUri = "testcheckout.buckaroo.nl/json/transaction"
        val postContent = getContent(amount, description, issuer)
        val httpMethod = "POST"
        val authHeader = authHeader(requestUri, postContent, httpMethod)
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.set("Authorization", authHeader)
        headers.set("Content-Type", "application/json")

        val entity = HttpEntity(postContent, headers)
        val res = restTemplate.postForObject("https://" + requestUri, entity, ObjectNode::class.java)
        return res.get("RequiredAction").get("RedirectURL").asText()

    }
}