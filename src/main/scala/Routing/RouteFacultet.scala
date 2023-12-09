package routing


import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import Model._
import Model.JsonFormatss
import org.json4s.{jackson}
import Repository.{RepoFacultet}

object RouteFacultet extends Json4sSupport {
  implicit val serialization = jackson.Serialization
  implicit val formats = JsonFormatss.formats

  val route =
    pathPrefix("Facultet") {
      concat(
        pathEnd {
          concat(
            get {
              complete(RepoFacultet.getAll())
            },
            post {
              entity(as[ModelFacultet]) { facultet =>
                complete(RepoFacultet.insertData(facultet))
              }
            }
          )
        },
        path(Segment) { id_facultet =>
          concat(
            get {
              complete(RepoFacultet.getById(id_facultet))
            },
            put {
              entity(as[ModelFacultet]) { update =>
                complete(RepoFacultet.updateData(update))
              }
            },
            delete {
              complete(RepoFacultet.deleteData(id_facultet))
            }
          )
        }
      )
    }
  }