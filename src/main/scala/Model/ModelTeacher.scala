package Model

import slick.jdbc.MySQLProfile.api._

case class ModelTeacher(     id_Teacher: Int,
                        name_Teacher:String
                   )extends Product with Serializable



class TeacherTable(tag: Tag) extends Table[ModelTeacher](tag, "teacher") {
  def id_Teacher = column[Int]("id_Teacher", O.PrimaryKey, O.AutoInc)
  def name_Teacher = column[String]("name_Teacher")

  def * = (id_Teacher,name_Teacher).mapTo[ModelTeacher]}