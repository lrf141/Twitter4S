package twitter4s.net.oauth

import java.net.{HttpURLConnection, URL}
import scala.collection.mutable.Map

/**
  * Created by lrf141 on 17/08/27.
  * @since 1.0.0
  */
class OAuthRequest {

  //oauth request end-point url
  private [this] val oauthRequestURL:String = "https://api.twitter.com/oauth/request_token"

  private [this] var callbackURL:String = ""

  private [this] val charSet:String = "URF-8"


  /**
    *
    * @param consumerKey
    * @param accessToken
    * @return
    */
  def sendOAuthRequest(consumerKey:String, accessToken:String):Int = {

    //make httpURLConnection object
    val url:URL = new URL(this.oauthRequestURL)
    val httpURLConnection:HttpURLConnection = url.openConnection.asInstanceOf[HttpURLConnection]

    //setting request method
    httpURLConnection.setRequestMethod("POST")
    httpURLConnection.setInstanceFollowRedirects(false)

    //setting request header
    httpURLConnection.setRequestProperty("oauth_consumer_key",consumerKey)
    httpURLConnection.setRequestProperty("oauth_nonce", getNonce)
    httpURLConnection.setRequestProperty("oauth_signature_method","HMAC-SHA1")
    httpURLConnection.setRequestProperty("oauth_timestamp",getTimestamp)
    httpURLConnection.setRequestProperty("oauth_token",accessToken)
    httpURLConnection.setRequestProperty("oauth_version", "1.0")
    0
  }


  /**
    * return oauth parameter as Map[String,String]
    * @param consumerKey
    * @param accessToken
    * @return parameter
    */
  def getOAuthParamMap(consumerKey:String, accessToken:String):Map[String,String] = {

    val paramMap:Map[String,String] = Map.empty[String,String]
    paramMap += "oauth_consumer_key" -> consumerKey
    paramMap += "oauth_nonce" -> getNonce
    paramMap += "oauth_signature_method" -> "HMAC-SHA1"
    paramMap += "oauth_timestamp" -> getTimestamp
    paramMap += "oauth_token" -> accessToken
    paramMap += "oauth_version" -> "1.0"

    paramMap
  }


  /**
    * set callback url
    * @param url wanna update url
    */
  def setCallbackURL(url:String):Unit =  this.callbackURL = url


  /**
    * @return now setting callback url return
    */
  def getCallbackURL:String = this.callbackURL

  /**
    * get time stamp
    * @return timestamp as String
    */
  def getTimestamp:String = {
    val millis:Long = System.currentTimeMillis
    val secs = millis/1000
    String.valueOf(secs)
  }

  /**
    * @return random value of String
    */
  def getNonce:String = String.valueOf(System.currentTimeMillis)
}
