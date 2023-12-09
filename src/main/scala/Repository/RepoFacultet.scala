package Repository

import DBManager.DBM
import DBManager.Main.materializer.executionContext
import Model._
import akka.http.javadsl.model.headers.ModeledCustomHeaderFactory
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.util.{Failure, Success}
object RepoFacultet {

  val FacultetQuery:TableQuery[FacultetTable] = TableQuery[FacultetTable]

  def insertData(data: ModelFacultet): Future[Int] = {
    val insertAction = FacultetQuery += data
    DBM.db.run(insertAction)
  }

  def updateData(updatedData: ModelFacultet): Future[Int] = {
    val updateAction = FacultetQuery.filter(_.id_Facultet === updatedData.id_Facultet)
      .map(facultet => (facultet.id_Facultet, facultet.name_Facultet))
      .update((updatedData.id_Facultet, updatedData.name_Facultet))

    DBM.db.run(updateAction)
  }

  def getAll(): Future[Seq[ModelFacultet]] = {
    val query = FacultetQuery.result
    DBM.db.run(query)
  }

  def getById(id: String): Future[Option[ModelFacultet]] = {
    val query = FacultetQuery.filter(_.id_Facultet === id.toInt).result.headOption
    DBM.db.run(query)
  }

  def deleteData(id: String): Future[Int] = {
    val deleteAction = FacultetQuery.filter(_.id_Facultet === id.toInt).delete
    DBM.db.run(deleteAction)
  }


}
