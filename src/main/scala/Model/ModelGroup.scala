package Model

import org.json4s.{CustomSerializer, DefaultFormats, Formats, MappingException}
import org.json4s.JsonAST.JString
import slick.jdbc.MySQLProfile.api._
object Language extends Enumeration{
  type Language = Value
  val English,Russian,Kazakh=Value

  implicit val languageColumnType: BaseColumnType[Language] =
    MappedColumnType.base[Language, String](_.toString, Language.withName)
}

object TypeOfTraning extends Enumeration{
  type TypeOfTraning=Value
  val Evening,Day=Value

  implicit val languageColumnType: BaseColumnType[TypeOfTraning] =
    MappedColumnType.base[TypeOfTraning, String](_.toString, TypeOfTraning.withName)
}

object TraningStage extends Enumeration{
  type TraningStage=Value
  val Bachelor_course,Magistracy,Doctoral_studies=Value

  implicit val languageColumnType: BaseColumnType[TraningStage] =
    MappedColumnType.base[TraningStage, String](_.toString, TraningStage.withName)
}

object EnumSerializer extends CustomSerializer[Enumeration#Value](format => (
  {
    case JString(s) =>
      if (Language.values.exists(_.toString == s)) {
        Language.withName(s)
      } else if (TypeOfTraning.values.exists(_.toString == s)) {
        TypeOfTraning.withName(s)
      } else if (TraningStage.values.exists(_.toString == s)) {
        TraningStage.withName(s)
      } else {
        throw new MappingException(s"Unknown enumeration value: $s")
      }
    case value =>
      throw new MappingException(s"Can't convert $value to Enumeration")
  },
  {
    case enumValue: Enumeration#Value =>
      JString(enumValue.toString)
  }
))

object JsonFormatss {
  implicit val formats: Formats = DefaultFormats + EnumSerializer
}
case class ModelGroup(   id_Group:Int,
                         name_Group:String,
                         facultet_Group:Int,
                         adviser_Group:Int,
                         language_Group:Language.Value,
                         type_of_Traning_Group:TypeOfTraning.Value,
                         course_Group:Int,
                         schedule_Group:Int,
                         elder_Group:Int,
                         traningStage_Group: TraningStage.Value
                        )extends Product with Serializable



class GroupTable(tag: Tag) extends Table[ModelGroup](tag, "group") {
  def id_Group = column[Int]("id_Group", O.PrimaryKey, O.AutoInc)
  def name_Group = column[String]("name_Group")
  def facultet_Group = column[Int]("facultet_Group")
  def adviser_Group = column[Int]("adviser_Group")
  def language_Group=column[Language.Language]("language_Group")
  def type_of_Traning_Group=column[TypeOfTraning.TypeOfTraning]("type_of_Traning_Group")
  def course_Group = column[Int]("course_Group")
  def schedule_Group = column[Int]("schedule_Group")
  def elder_Group = column[Int]("elder_Group")
  def traningStage_Group=column[TraningStage.TraningStage]("traningStage_Group")

  def * = (id_Group, name_Group, facultet_Group, adviser_Group,language_Group,type_of_Traning_Group, course_Group, schedule_Group, elder_Group, traningStage_Group).mapTo[ModelGroup]
}






