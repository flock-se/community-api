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


//    val willem = Member(id = 0, name = "Willem", status = "Terminated")

//    willem.name

    app.get("/members") { ctx -> ctx.json(ArrayList(memberDao.members.values))}
}