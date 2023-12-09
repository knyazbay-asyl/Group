package routing

import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import Model._
import Model.JsonFormatss
import org.json4s.{DefaultFormats, jackson}
import Repository.{RepoFacultet, RepoGroup, RepoStudent}

object RouteStudent extends Json4sSupport {
  implicit val serialization = jackson.Serialization
  implicit val formats = JsonFormatss.formats

  val route =
    pathPrefix("Student") {
      concat(
        pathEnd {
          concat(
            get {
              complete(RepoStudent.getAll())
            },
            post {
              entity(as[ModelStudent]) { student =>
                complete(RepoStudent.insertData(student))
              }
            }
          )
        },
        path(Segment) { id_student =>
          concat(
            get {
              complete(RepoStudent.getById(id_student))
            },
            put {
              entity(as[ModelStudent]) { update =>
                complete(RepoStudent.updateData(update))
              }
            },
            delete {
              complete(RepoStudent.deleteData(id_student))
            }
          )
        }
      )
    }
  }