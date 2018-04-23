import Member
import java.util.concurrent.atomic.AtomicInteger

class MemberDao {

    val members = hashMapOf(
        0 to Member(id = 0, name = "Willem Kersten", status = "Active"),
        1 to Member(id = 1, name = "Willem Veelenturf", status = "Active"),
        2 to Member(id = 2, name = "Maureen van der Sluis", status = "Active"),
        3 to Member(id = 3, name = "Vincent de Bruijn", status = "Active"),
        4 to Member(id = 4, name = "Jerre van Veluw", status = "Active"),
        5 to Member(id = 5, name = "Jordi Franken", status = "Active"),
        6 to Member(id = 5, name = "Freddie", status = "Active")

    )

    var lastId: AtomicInteger = AtomicInteger(members.size - 1)

    fun findById(id: Int): Member? {
        return members[id]
    }

    fun findByName(name: String): Member? {
        return members.values.find { it.name.toLowerCase().contains(name.toLowerCase()) }
    }

    fun update(id: Int, member: Member) {
        members.put(id, Member(id = member.id, name = member.name, status = member.status))
    }

    fun delete(id: Int) {
        members.remove(id)
    }

}