package com.flock.community.api.services

import com.flock.community.api.model.Member
import com.google.cloud.datastore.DatastoreOptions
import com.google.cloud.datastore.Entity
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger
import com.google.datastore.v1.client.DatastoreHelper.getKey
import java.awt.print.Book
import com.google.cloud.datastore.IncompleteKey
import com.google.cloud.datastore.FullEntity
import sun.security.rsa.RSAPrivateCrtKeyImpl.newKey



@Service
class MemberService() {


    val datastore = DatastoreOptions.getDefaultInstance().getService()
    val keyFactory = datastore.newKeyFactory().setKind("Members")

    val members = listOf(
        Member(id = 0, name = "Willem Kersten", status = "Active"),
        Member(id = 1, name = "Willem Veelenturf", status = "Active"),
        Member(id = 2, name = "Maureen van der Sluis", status = "Active"),
        Member(id = 3, name = "Vincent de Bruijn", status = "Active"),
        Member(id = 4, name = "Jerre van Veluw", status = "Active"),
        Member(id = 5, name = "Jordi Franken", status = "Active"),
        Member(id = 6, name = "Freddie", status = "Active")

    )

    var lastId: AtomicInteger = AtomicInteger(members.size - 1)

    fun findById(id: Int): Member? {
        return members.find { x -> x.id == id }
    }

    fun findByName(name: String): Member? {
        return members.find { x -> x.name.toLowerCase() == name.toLowerCase() }
    }

    fun findAll(): List<Member> {
        return members
    }

    fun create(): Long? {
        val key = keyFactory.newKey()
        val incBookEntity = Entity.newBuilder(key)
            .set("name", "Willem Veelenturf")
            .build()
        val bookEntity = datastore.add(incBookEntity)
        return bookEntity.key.id
    }

//    fun update(id: Int, member: Member) {
//        members.put(id, Member(id = member.id, name = member.name, status = member.status))
//    }
//
//    fun delete(id: Int) {
//        members.remove(id)
//    }

}