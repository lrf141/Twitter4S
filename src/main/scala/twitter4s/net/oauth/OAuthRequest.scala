package twitter4s.net.oauth

import java.net.URLEncoder
import javax.crypto.{Mac, SecretKey}
import javax.crypto.spec.SecretKeySpec

import scala.collection.mutable.TreeMap
import org.apache.commons.codec.binary.Base64

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
    *
    * @param consumerKey
    * @param accessToken
    * @return
    */
  def getOauthParamMap(consumerKey: String, accessToken: String):mutable.TreeMap[String,String] = {

    var paramMap:mutable.TreeMap[String,String] = mutable.TreeMap.empty[String,String]

    paramMap += "oauth_consumer_key" -> consumerKey
    paramMap += "oauth_nonce" -> getNonce
    paramMap += "oauth_signature_method" -> "HMAC-SHA1"
    paramMap += "oauth_timestamp" -> getTimestamp
    paramMap += "oauth_token" -> accessToken
    paramMap += "oauth_version" -> "1.0"

    paramMap

  }

  /**
    * @param method
    * @param url
    * @param urlParam
    * @param oauthParam
    * @return
    */
  def getSignatureBaseString(method: String, url: String, urlParam: mutable.TreeMap[String,String], oauthParam: mutable.TreeMap[String,String]):String = {

    val paramString:StringBuffer = new StringBuffer

    var treeMap:mutable.TreeMap[String,String] = mutable.TreeMap.empty[String,String]

    urlParam.keys.foreach({ key =>
      treeMap += key -> urlParam(key)
    })
    oauthParam.keys.foreach({ key =>
      treeMap += key -> oauthParam(key)
    })

    for(keys <- treeMap.keys)
      paramString.append("&" + keys + "=" + treeMap(keys))

    val template:String = "%s&%s&%s"
    val signature:String = String.format(
      template,
      URLEncoder.encode(method, this.charSet),
      URLEncoder.encode(url, this.charSet),
      URLEncoder.encode(paramString.toString.tail, this.charSet)
    )

    signature
  }

  /**
    * @param signature
    * @param paramMap
    * @param consumerSecret
    * @param accessSecret
    * @return
    */
  def getAuthHeader(signature: String, paramMap: TreeMap[String,String], consumerSecret: String, accessSecret: String):String = {
    val compositeKey:String = URLEncoder.encode(consumerSecret,this.charSet) + "&" + URLEncoder.encode(accessSecret, this.charSet)
    val oauthSignature:String = computeSignature(signature,compositeKey)
    val oauthSignatureEncoded:String = URLEncoder.encode(oauthSignature,this.charSet)

    val authorizationHeaderValueTemp:String =
      "OAuth oauth_consumer_key=\"%s\", oauth_nonce=\"%s\", oauth_signature=\"%s\", " +
        "oauth_signature_method=\"%s\", oauth_timestamp=\"%s\", oauth_token=\"%s\", oauth_version=\"%s\"";

    String.format(
      authorizationHeaderValueTemp,
      paramMap("oauth_consumer_key"),
      paramMap("oauth_nonce"),
      oauthSignatureEncoded,
      paramMap("oauth_signature_method"),
      paramMap("oauth_timestamp"),
      paramMap("oauth_token"),
      paramMap("oauth_version")
    )
  }


  /**
    * @param url
    * @param paramMap
    * @return
    */
  def getURLWithParam(url: String, paramMap: mutable.TreeMap[String,String]):String = {

    val strBuffer:StringBuffer = new StringBuffer(url)
    var treeMap:mutable.TreeMap[String,String] = mutable.TreeMap.empty[String,String]
    for(keys <- paramMap.keys)
      treeMap += keys -> paramMap(keys)

    for(keys <- treeMap.keys){
      if(keys == treeMap.firstKey)
        strBuffer.append("?")
      else
        strBuffer.append("&")
      strBuffer.append(keys + "=" + treeMap(keys))
    }

    strBuffer.toString
  }


  /**
    * @param baseString
    * @param keyString
    * @return
    */
  def computeSignature(baseString: String, keyString: String):String = {
    val keyBytes = keyString.getBytes
    val secretKey:SecretKey = new SecretKeySpec(keyBytes,"HmacSHA1")

    val mac:Mac = Mac.getInstance("HmacSHA1")
    mac.init(secretKey)

    val text:Array[Byte] = baseString.getBytes
    new String(Base64.encodeBase64(mac.doFinal(text))).trim
  }

  /**
    * set callback url
    * @param url wanna update url
    */
  def setCallbackURL(url: String):Unit =  this.callbackURL = url


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
  def getUrlEncode(target: String):String = URLEncoder.encode(target,this.charSet)
}
