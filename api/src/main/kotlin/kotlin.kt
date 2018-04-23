//fun main(args: Array<String>) {
//    println("Crack cocaine?")
//}
//

import io.javalin.ApiBuilder.*
import io.javalin.Javalin
//import Member
import MemberDao

fun main(args: Array<String>) {
    val memberDao = MemberDao();

//    val app = Javalin.start(7000)
////    app.get("/") { ctx -> ctx.result("Hello World") }

    val app = Javalin.create().apply {
//        enableCorsForOrigin("//localhost:8080")
        enableCorsForAllOrigins()
        port(7000)
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start()

    app.routes {
        get("/members") { ctx ->
            ctx.json(ArrayList(memberDao.members.values))
        }

        get("/members/name/:name") { ctx ->
            ctx.json(memberDao.findByName(ctx.param("name")!!)!!)
        }

        patch("/members/update/:id") { ctx ->
            val member = ctx.bodyAsClass(Member::class.java)
            memberDao.update(
                    id = ctx.param("id")!!.toInt(),
                    member = member
            )
            ctx.status(204)
        }
    }
}