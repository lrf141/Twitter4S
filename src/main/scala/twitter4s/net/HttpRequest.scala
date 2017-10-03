package twitter4s.net

import scala.collection.JavaConverters._
import java.net.{HttpURLConnection, URL, URLConnection}
import java.io.{BufferedReader, InputStream, InputStreamReader}

import twitter4s.APIKeys
import twitter4s.net.oauth.OAuthRequest

import scala.collection.mutable


/**
  * Created by lrf141 on 17/08/26.
  *
  * @author lrf141
  * @since 1.0.0
  */
class HttpRequest(_keys: APIKeys) {

  private[this] val endpointBaseURL: String = "https://api.twitter.com/1.1/"
  private[this] val encodeType: String = "UTF-8"

  private[this] val POST: String = "POST"
  private[this] val GET: String = "GET"

  private[this] var apiKeys: APIKeys = _keys

  /**
    * send GET request
    * @param uri entry point. ex) status/update.json
    * @param param http parameter
    * @return response header as String
    */
  def get(uri: String, param: mutable.TreeMap[String, String]): String = {

    //make entry point
    val url: String = this.endpointBaseURL + uri

    //get api keys
    val keys: List[String] = this.apiKeys.getKeysAsList

    //make signature
    val oauthMap = OAuthRequest.getOauthParamMap(keys(0), keys(2))
    val urlwithparam: String = OAuthRequest.getURLWithParam(url, param)
    val signature: String = OAuthRequest.getSignatureBaseString(this.GET, url, param, oauthMap)
    val authSignature: String = OAuthRequest.getOAuthHeader(signature, oauthMap, keys(1), keys(3))

    //add authorization header
    val urlConnection: HttpURLConnection = new URL(urlwithparam).openConnection.asInstanceOf[HttpURLConnection]
    urlConnection.setRequestProperty("Authorization", authSignature)

    //get request response body
    val br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream))
    convertBody(br)
  }


  /**
    * send POST request
    * @param uri entry point. ex) status/update.json
    * @param param http parameter
    * @return response header as String
    */
  def post(uri: String, param: mutable.TreeMap[String, String]): String = {

    val url: String = this.endpointBaseURL + uri

    val keys: List[String] = this.apiKeys.getKeysAsList

    val oauthMap = OAuthRequest.getOauthParamMap(keys(0), keys(2))
    val urlWithParam: String = OAuthRequest.getURLWithParam(url, param)
    val signature: String = OAuthRequest.getSignatureBaseString(this.POST, url, param, oauthMap)
    val authSignature: String = OAuthRequest.getOAuthHeader(signature, oauthMap, keys(1), keys(3))

    val urlConnection: HttpURLConnection = new URL(urlWithParam).openConnection.asInstanceOf[HttpURLConnection]
    urlConnection.setRequestMethod(this.POST)
    urlConnection.setRequestProperty("Authorization", authSignature)
    urlConnection.connect()

    val br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream))
    convertBody(br)

  }

  /**
    * get a http request response
    *
    * @param connection HttpURLConnection Object
    * @return
    */
  def getResponse(connection: URLConnection): HttpResponse = {

    val httpResponse: HttpResponse = new HttpResponse

    //get response header
    val headerMap = connection.getHeaderFields.asScala.toMap.mapValues(l => l.asScala.toList)

    val resultMap: Map[String, String] = convertHeader(headerMap)
    httpResponse.setResponseHeader(resultMap)

    //get response body
    val inputStream: InputStream = connection.getInputStream
    val reader: BufferedReader = new BufferedReader(new InputStreamReader(inputStream, this.encodeType))
    val responseBody: String = convertBody(reader)
    httpResponse.setResponseBody(responseBody)

    httpResponse
  }

  /**
    * when we get http headers, we get it as Map[String,List[String].
    * this function convert it into Map[String,String].
    *
    * @param rawHeaderMap raw header
    * @return converted header as Map[String,String]
    */
  private def convertHeader(rawHeaderMap: Map[String, List[String]]): Map[String, String] = {
    rawHeaderMap.filterKeys(_ != null).mapValues(_.mkString("\n") + "\n")
  }

  /**
    * when we get response header, we get it as Buffer.
    * this function convert it into String.
    *
    * @param reader body contents buffer
    * @return response body as String data
    */
  private def convertBody(reader: BufferedReader): String = {
    Iterator.continually(reader.readLine()).takeWhile(_ != null).mkString("\n") + "\n"
  }

  /**
    * @param requestHeaderMap
    * @param header
    * @return
    */
  def makeRequestHeader(header: HttpURLConnection, requestHeaderMap: Map[String, String]): HttpURLConnection = {
    val httpRequestHeader: HttpURLConnection = header
    requestHeaderMap.foreach {
      case (key, str) => httpRequestHeader.setRequestProperty(key, str)
    }
    httpRequestHeader
  }

  /**
    * @param api
    */
  def setApiKeys(api: APIKeys): Unit = this.apiKeys = api
}
