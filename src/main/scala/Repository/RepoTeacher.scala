package Repository

import DBManager.DBM
import DBManager.Main.materializer.executionContext
import Model._
import akka.http.javadsl.model.headers.ModeledCustomHeaderFactory
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.Future
import scala.util.{Failure, Success}
object RepoTeacher {

  val TeacherQuery:TableQuery[TeacherTable] = TableQuery[TeacherTable]

  def insertData(data: ModelTeacher): Future[Int] = {
    val insertAction = TeacherQuery += data
    DBM.db.run(insertAction)
  }

  def updateData(updatedData: ModelTeacher): Future[Int] = {
    val updateAction = TeacherQuery.filter(_.id_Teacher === updatedData.id_Teacher)
      .map(teacher => (teacher.id_Teacher, teacher.name_Teacher))
      .update((updatedData.id_Teacher, updatedData.name_Teacher))

    DBM.db.run(updateAction)
  }

  def getAll(): Future[Seq[ModelTeacher]] = {
    val query = TeacherQuery.result
    DBM.db.run(query)
  }

  def getById(id: String): Future[Option[ModelTeacher]] = {
    val query = TeacherQuery.filter(_.id_Teacher === id.toInt).result.headOption
    DBM.db.run(query)
  }

  def deleteData(id: String): Future[Int] = {
    val deleteAction = TeacherQuery.filter(_.id_Teacher === id.toInt).delete
    DBM.db.run(deleteAction)
  }
}
