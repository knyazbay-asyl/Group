package Model

import slick.jdbc.MySQLProfile.api._

case class ModelFacultet(     id_Facultet: Int,
                        name_Facultet:String
                  )extends Product with Serializable



class FacultetTable(tag: Tag) extends Table[ModelFacultet](tag, "facultet") {
  def id_Facultet = column[Int]("id_Facultet", O.PrimaryKey, O.AutoInc)
  def name_Facultet = column[String]("name_Facultet")

  def * = (id_Facultet,name_Facultet).mapTo[ModelFacultet]}