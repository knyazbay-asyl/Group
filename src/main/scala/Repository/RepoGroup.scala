package Repository

import DBManager.DBM
import DBManager.Main.materializer.executionContext
import Model._
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.Future
import scala.util.{Failure, Success}


object RepoGroup {
  val GroupQuery:TableQuery[GroupTable] = TableQuery[GroupTable]
  def insertData(data: ModelGroup): Future[Int] = {
    val insertAction = GroupQuery += data
    DBM.db.run(insertAction)
  }

  def updateData(updatedData: ModelGroup): Future[Int] = {
    val updateAction = GroupQuery.filter(_.id_Group === updatedData.id_Group)
      .map(group => (group.id_Group, group.name_Group, group.facultet_Group, group.adviser_Group, group.course_Group, group.schedule_Group, group.elder_Group))
      .update((updatedData.id_Group, updatedData.name_Group, updatedData.facultet_Group, updatedData.adviser_Group, updatedData.course_Group, updatedData.schedule_Group, updatedData.elder_Group))

    DBM.db.run(updateAction)
  }

  def getAll(): Future[Seq[ModelGroup]] = {
    val query = GroupQuery.result
    DBM.db.run(query)
  }

  def getById(id: String): Future[Option[ModelGroup]] = {
    val query = GroupQuery.filter(_.id_Group === id.toInt).result.headOption
    DBM.db.run(query)
  }

  def deleteData(id: String): Future[Int] = {
    val deleteAction = GroupQuery.filter(_.id_Group === id.toInt).delete
    DBM.db.run(deleteAction)
  }


}
