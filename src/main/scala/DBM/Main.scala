package DBManager

import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{Await}
import scala.concurrent.duration._
import akka.http.scaladsl.server.Directives._
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import org.json4s.{DefaultFormats, jackson}
import scala.concurrent.{ExecutionContextExecutor}
import routing._



object DBM {
  val db: Database = Database.forConfig("mysqlDB")
}



object Main extends Json4sSupport {
  implicit val system: ActorSystem = ActorSystem("web-service")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  implicit val serialization = jackson.Serialization
  implicit val formats = DefaultFormats

  def main(args: Array[String]): Unit = {

    val allRoutes = RouteStudent.route ~ RouteGroup.route ~ RouteTeacher.route ~ RouteFacultet.route

    val bindingFuture = Http().bindAndHandle(allRoutes, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

    scala.io.StdIn.readLine()

    bindingFuture
      .flatMap { binding =>
        println(s"Unbinding from ${binding.localAddress}")
        binding.unbind()
      }
      .onComplete { _ =>
        println("Terminating server and actor system...")
        system.terminate()
      }

    Await.result(system.whenTerminated, 10.seconds)
  }
}
