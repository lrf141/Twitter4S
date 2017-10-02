package twitter4s.util

import io.circe.parser._
import io.circe.generic.auto._
import twitter4s._

/**
  * Created by lrf141 on 17/09/23.
  * Json decode using circe lib
  * @since 1.0.0
  * @author lrf141
  */
object JsonDecoder {


  /**
    * Use this for json data of User TimeLine
    * @param jsonText json data as String
    * @return parse result as Seq[HomeTimeLine]
    */
  def decodeHomeTimeLine(jsonText: String):Seq[HomeTimeLine] = parse(jsonText).flatMap(_.as[Seq[HomeTimeLine]]) match {
      case Right(values) => values
      case Left(error) => null
  }

  /**
    * Use this for json data of User TimeLine
    * @param jsonText json data as String
    * @return parse result as Seq[UserTimeLine]
    */
  def decodeUserTimeLine(jsonText: String): Seq[UserTimeLine] = parse(jsonText).flatMap(_.as[Seq[UserTimeLine]]) match {
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

  /**
    * Use this for json data of TweetStatus
    * @param jsonText json data as String
    * @return status update as TweetStatus
    */
  def decodeTweetStatus(jsonText: String):Status = decode[Status](jsonText) match {
    case Right(values) => values
    case Left(error) => null
  }

  /**
    * @param jsonText
    * @return
    */
  def decodeTweetSearch(jsonText: String):Tweets = decode[Tweets](jsonText) match {
    case Right(values) => values
    case Left(error) => null
  }

}