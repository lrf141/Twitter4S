package twitter4s

import twitter4s.net.HttpRequest
import twitter4s.net.oauth.OAuthRequest
import twitter4s.util.JsonDecoder

import scala.collection.mutable


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
  private [this] var httpRequest:HttpRequest = null


  /**
    * get your home timeline and show on terminal
    */
  def getHomeTimeLine: Seq[UserTimeLine] = {

    val uri:String = "statuses/home_timeline.json"

    //get as Json
    val result:String = httpRequest.get(uri,mutable.TreeMap.empty[String,String])

    //parse tweet status
    val homeTimeLine = JsonDecoder.decodeUserTimeLine(result)

    homeTimeLine.foreach{tweet =>

      //append tweet status
      val status:StringBuilder = new StringBuilder
      status.append(tweet.user.name +" @"+ tweet.user.screen_name)
      status.append(tweet.text + ": " + tweet.created_at)

      println(status.toString)
    }

    homeTimeLine

  }

  /**
    * get your followers list
    * @return followers list as Seq[UserStatus]
    */
  def getFollowersList:Seq[UserStatus] = {

    val uri:String = "followers/list.json"

    val requestParam:mutable.TreeMap[String,String] = mutable.TreeMap.empty[String, String]
    requestParam += "cursor" -> "-1"

    val response_json:String = httpRequest.get(uri,requestParam)
    JsonDecoder.decodeUserArray(response_json)

  }


  /**
    * get friends(follow) user list as Seq[UserStatus]
    * @return
    */
  def getFriendsList:Seq[UserStatus] = {
    val uri:String = "friends/list.json"

    val requestParam:mutable.TreeMap[String,String] = mutable.TreeMap.empty[String, String]
    requestParam += "cursor" -> "-1"

    val response_json:String = httpRequest.get(uri,requestParam)
    JsonDecoder.decodeUserArray(response_json)
  }


  /**
    * post your tweet on twitter by update status
    * @param tweet your tweet as String
    */
  def updateStatus(tweet: String):Unit = {

    //tweet data is up to 140 chars
    if(140 < tweet.length){
      throw new Exception
    }

    val uri:String = "statuses/update.json"

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
  def initialize(_ck: String, _cs: String, _at: String, _as: String):Unit = {
    this.apiKeys.setKeys(_ck,_cs,_at,_as)
    this.httpRequest = new HttpRequest(this.apiKeys)
  }

}
