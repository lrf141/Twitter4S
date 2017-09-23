package twitter4s.core

import twitter4s.net.HttpRequest
import twitter4s.net.oauth.OAuthRequest
import twitter4s.{TimeLineUser, UserArray, UserStatus, UserTimeLine}

import scala.collection.mutable
import io.circe.parser._
import io.circe.generic.auto._


/**
  * Created by lrf141 on 17/08/26.
  * twitter4s is a Scala wrapper for the Twitter API.
  * this lib is using REST API
 *
  * @author lrf141
  * @since 1.0.0
  */
class Twitter4s {

  private [this] val apiKeys:APIKeys = new APIKeys


  /**
    * get your home timeline and show on terminal
    */
  def getHomeTimeLine: Unit = {

    val uri:String = "statuses/home_timeline.json"
    val httpRequest:HttpRequest = new HttpRequest(apiKeys)
    httpRequest.setApiKeys(this.apiKeys)

    //get as Json
    val result:String = httpRequest.get(uri,mutable.TreeMap.empty[String,String])

    //parse tweet status
    val homeTimeLine:Seq[UserTimeLine] = parse(result).flatMap(_.as[Seq[UserTimeLine]]) match {
      case Right(tweets) => tweets
      case Left(error) => {
        println(error)
        null
      }

    }

    homeTimeLine.foreach{tweet =>

      //append tweet status
      val status:StringBuilder = new StringBuilder
      status.append(tweet.user.name +" @"+ tweet.user.screen_name)
      status.append(tweet.text + ": " + tweet.created_at)

      println(status.toString)
    }

  }

  /**
    *
    */
  def getFollowersList():Seq[UserStatus] = {

    val uri:String = "followers/list.json"
    val httpRequest:HttpRequest = new HttpRequest(apiKeys)
    httpRequest.setApiKeys(this.apiKeys)

    val requestParam:mutable.TreeMap[String,String] = mutable.TreeMap.empty[String, String]
    requestParam += "cursor" -> "-1"

    val response_json:String = httpRequest.get(uri,requestParam)
    decode[UserArray](response_json) match {
      case Right(userList) => userList.users
      case Left(error) => null
    }

  }


  /**
    * post your tweet on twitter by update status
    * @param tweet your tweet as String
    */
  def updateStatus(tweet: String):Unit = {
    val uri:String = "statuses/update.json"
    val httpRequest:HttpRequest = new HttpRequest(apiKeys)
    httpRequest.setApiKeys(this.apiKeys)
    val requestParam:mutable.TreeMap[String,String] = mutable.TreeMap.empty[String,String]
    //included +, return 401
    requestParam += "status" -> OAuthRequest.getUrlEncode(tweet).replace("+","%20")

    httpRequest.post(uri,requestParam)
  }

  /**
    * @param _ck consumer key
    * @param _cs consumer secret key
    * @param _at access token
    * @param _as access token secret
    */
  def setAPIKeys(_ck: String, _cs: String, _at: String, _as: String):Unit = this.apiKeys.setKeys(_ck,_cs,_at,_as)

  /**
    * @return apiKeys instance
    */
  def getAPIKeys = this.apiKeys

}
