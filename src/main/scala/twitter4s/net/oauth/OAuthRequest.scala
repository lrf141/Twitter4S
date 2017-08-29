package twitter4s.net.oauth

import java.net.{HttpURLConnection, URL, URLEncoder}
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

import scala.collection.mutable.{Map, SortedMap}
import twitter4s.core.APIKeys
import twitter4s.util.Base64Translator

import scala.collection.mutable

/**
  * Created by lrf141 on 17/08/27.
  * @since 1.0.0
  */
object OAuthRequest {

  //oauth request end-point url
  private [this] val oauthRequestURL:String = "https://api.twitter.com/oauth/request_token"

  private [this] var callbackURL:String = ""

  private [this] val charSet:String = "UTF-8"

  private [this] val oauthTokenSecret:String = ""


  /**
    * return oauth parameter as Map[String,String]
    * @param consumerKey
    * @param accessToken
    * @return parameter
    */
  def getOAuthParamMap(consumerKey:String, accessToken:String):SortedMap[String,String] = {

    val paramMap:SortedMap[String,String] = SortedMap.empty[String,String]
    paramMap += "oauth_consumer_key" -> consumerKey
    paramMap += "oauth_nonce" -> getNonce
    paramMap += "oauth_signature_method" -> "HMAC-SHA1"
    paramMap += "oauth_timestamp" -> getTimestamp
    paramMap += "oauth_token" -> accessToken
    paramMap += "oauth_version" -> "1.0"

    paramMap
  }

  /**
    * @param params
    * @return
    */
  private def makeOauthSignature(params:SortedMap[String,String]):String = {

    var paramString: String = ""
    for (keys <- params.keys)
      paramString += s"&${keys}=${params(keys)}"

    //remove first "&"
    paramString.tail
  }

  /**
    *
    * @param consumerSecret
    * @param method
    * @param params
    * @return
    */
  def makeSignature(consumerSecret:String,accessToken:String,method:String,params:SortedMap[String,String]):String = {

    val target:String = makeOauthSignature(params)

    //make signature key
    val signatureKey:String = getUrlEncode(consumerSecret) + "&" + getUrlEncode(accessToken)
    val signedKey:SecretKeySpec = new SecretKeySpec(signatureKey.getBytes,"HmacSHA1")
    val mac:Mac = Mac.getInstance(signedKey.getAlgorithm)
    mac.init(signedKey)
    val rawHmac:Array[Byte] = mac.doFinal(target.getBytes)

    //return signature key
    Base64Translator.encode(rawHmac.toString)
  }

  /**
    * @param params
    * @return
    */
  def makeAuthorizationHeader(params:SortedMap[String,String]):String = {
    var paramStr: String = ""
    for(keys <- params.keys)
      paramStr += ", " + keys + "=\"" + getUrlEncode(params(keys)) + "\""
    paramStr.drop(2)
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

  /**
    * @param target
    * @return
    */
  def getUrlEncode(target:String):String = URLEncoder.encode(target,this.charSet)
}
