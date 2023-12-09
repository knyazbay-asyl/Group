package Model

import slick.jdbc.MySQLProfile.api._

case class ModelStudent(     id: Int,
                         id_Group:Int,
                         id_Student:Int
                        )extends Product with Serializable



class StudentTable(tag: Tag) extends Table[ModelStudent](tag, "student") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def id_Group = column[Int]("id_Group")
  def id_Student = column[Int]("id_Student")

  def * = (id,id_Group, id_Student).mapTo[ModelStudent]
}







