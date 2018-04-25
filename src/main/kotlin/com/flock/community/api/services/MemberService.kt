package com.flock.community.api.services

import com.flock.community.api.model.Member
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

@Service
class MemberService () {

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

//    fun update(id: Int, member: Member) {
//        members.put(id, Member(id = member.id, name = member.name, status = member.status))
//    }
//
//    fun delete(id: Int) {
//        members.remove(id)
//    }

}