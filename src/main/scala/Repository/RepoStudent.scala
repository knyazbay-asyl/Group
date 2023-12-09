package Repository

import DBManager.DBM
import DBManager.Main.materializer.executionContext
import Model._
import akka.http.javadsl.model.headers.ModeledCustomHeaderFactory
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future
import scala.util.{Failure, Success}
object RepoStudent {

  val StudentQuery:TableQuery[StudentTable] = TableQuery[StudentTable]

  def insertData(data: ModelStudent): Future[Int] = {
    val insertAction = StudentQuery += data
    DBM.db.run(insertAction)
  }

  def updateData(updatedData: ModelStudent): Future[Int] = {
    val updateAction = StudentQuery.filter(_.id === updatedData.id)
      .map(student => (student.id, student.id_Group,student.id_Student))
      .update((updatedData.id, updatedData.id_Group,updatedData.id_Student))
    DBM.db.run(updateAction)
  }

  def getAll(): Future[Seq[ModelStudent]] = {
    val query = StudentQuery.result
    DBM.db.run(query)
  }

  def getById(id: String): Future[Option[ModelStudent]] = {
    val query = StudentQuery.filter(_.id === id.toInt).result.headOption
    DBM.db.run(query)
  }

  def deleteData(id: String): Future[Int] = {
    val deleteAction = StudentQuery.filter(_.id === id.toInt).delete
    DBM.db.run(deleteAction)
  }


}
