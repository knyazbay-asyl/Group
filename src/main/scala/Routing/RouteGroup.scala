package routing


import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpjson4s.Json4sSupport
import Model._
import Model.JsonFormatss
import org.json4s.{DefaultFormats, jackson}
import Repository.RepoGroup

object RouteGroup extends Json4sSupport {
  implicit val serialization = jackson.Serialization
  implicit val formats = JsonFormatss.formats

  val route =
    pathPrefix("Group") {
      concat(
        pathEnd {
          concat(
            get {
              complete(RepoGroup.getAll())
            },
            post {
              entity(as[ModelGroup]) { group =>
                complete(RepoGroup.insertData(group))
              }
            }
          )
        },
        path(Segment) { id_group =>
          concat(
            get {
              complete(RepoGroup.getById(id_group))
            },
            put {
              entity(as[ModelGroup]) { update =>
                complete(RepoGroup.updateData(update))
              }
            },
            delete {
              complete(RepoGroup.deleteData(id_group))
            }
          )
        }
      )
    }
  }