package twitter4s.util

import io.circe.parser._
import io.circe.generic.auto._
import twitter4s.{UserArray, UserStatus, UserTimeLine}

/**
  * Created by lrf141 on 17/09/23.
  */
object JsonDecoder {


  /**
    * Use this for json data of User TimeLine
    * @param jsonText json data as String
    * @return parse result as Seq[T]
    */
  def decodeUserTimeLine(jsonText: String):Seq[UserTimeLine] = parse(jsonText).flatMap(_.as[Seq[UserTimeLine]]) match {
      case Right(values) => values
      case Left(error) => null
  }

  /**
    * Use this for json data of User Arrays
    * @param jsonText json data as String
    * @return user status as Seq[UserStatus]
    */
  def decodeUserArray(jsonText: String):Seq[UserStatus] = decode[UserArray](jsonText) match {
    case Right(values) => values.users
    case Left(error) => null
  }

}
