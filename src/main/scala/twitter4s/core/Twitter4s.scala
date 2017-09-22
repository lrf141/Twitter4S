package twitter4s.core

import twitter4s.net.HttpRequest
import twitter4s.net.oauth.OAuthRequest

import scala.collection.mutable
import scala.util.matching.Regex

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
    *
    */
  def getHomeTimeLine: Unit = {

    val uri:String = "statuses/home_timeline.json"
    val httpRequest:HttpRequest = new HttpRequest(apiKeys)
    httpRequest.setApiKeys(this.apiKeys)

    //get as Json
    val result:String = httpRequest.get(uri,mutable.TreeMap.empty[String,String])

    //remove first array []
    val simpleJson:String = result.trim.tail.init + ",{\"c"

    println(simpleJson.split(",{\"c"))
  }

  /**
    * @param tweet
    */
  def updateStatus(tweet: String):Unit = {
    val uri:String = "statuses/update.json"
    val httpRequest:HttpRequest = new HttpRequest(apiKeys)
    httpRequest.setApiKeys(this.apiKeys)
    val requestParam:mutable.TreeMap[String,String] = mutable.TreeMap.empty[String,String]
    //included +, return 401
    requestParam += "status" -> OAuthRequest.getUrlEncode(tweet).replace("+","%20")

    val result:String = httpRequest.post(uri,requestParam)
  }

  /**
    * @param _ck consumer key
    * @param _cs consumer secret key
    * @param _at access token
    * @param _as access token secret
    */
  def setAPIKeys(_ck: String, _cs: String, _at: String, _as: String):Unit = this.apiKeys.setKeys(_ck,_cs,_at,_as)

  def getAPIKeys = this.apiKeys

}
