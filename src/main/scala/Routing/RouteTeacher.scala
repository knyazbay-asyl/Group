package routing

import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import Model._
import Model.JsonFormatss
import org.json4s.{DefaultFormats, jackson}
import Repository.{RepoFacultet, RepoGroup, RepoStudent, RepoTeacher}

object RouteTeacher extends Json4sSupport {
  implicit val serialization = jackson.Serialization
  implicit val formats = JsonFormatss.formats

  val route =
    pathPrefix("Teacher") {
      concat(
        pathEnd {
          concat(
            get {
              complete(RepoTeacher.getAll())
            },
            post {
              entity(as[ModelTeacher]) { teacher =>
                complete(RepoTeacher.insertData(teacher))
              }
            }
          )
        },
        path(Segment) { id_teacher =>
          concat(
            get {
              complete(RepoTeacher.getById(id_teacher))
            },
            put {
              entity(as[ModelTeacher]) { update =>
                complete(RepoTeacher.updateData(update))
              }
            },
            delete {
              complete(RepoTeacher.deleteData(id_teacher))
            }
          )
        }
      )
    }
  }