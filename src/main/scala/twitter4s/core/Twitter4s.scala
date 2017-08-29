package twitter4s.core

import twitter4s.net.{HttpResponse, HttpRequest}
import scala.collection.mutable.Map

/**
  * Created by lrf141 on 17/08/26.
  * twitter4s is a Scala wrapper for the Twitter API.
  * this lib is using REST API
 *
  * @author lrf141
  * @since 1.0.0
  */
class Twitter4s {


  def getHomeTimeLine(): Unit = {
    val uri:String = "statuses/home_timeline.json"
    val result:HttpResponse = HttpRequest.post(uri,Map.empty[String,String])
    println(result.getResponseBody)
  }

  /**
    * @param _ck consumer key
    * @param _cs consumer secret key
    * @param _at access token
    * @param _as access token secret
    */
  def setAPIKeys(_ck:String, _cs:String, _at:String, _as:String):Unit = APIKeys.setKeys(_ck,_cs,_at,_as)

  /**
    * @param _ck consumer key
    * @param _cs consumer secret key
    */
  def setAPIKeys(_ck:String, _cs:String):Unit = APIKeys.setKeys(_ck,_cs)

}
