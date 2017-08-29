package twitter4s.net

import scala.collection.mutable.{Map, SortedMap}
import scala.collection.JavaConverters._
import java.net.{HttpURLConnection, URL, URLEncoder}
import java.io.{BufferedReader, IOException, InputStream, InputStreamReader, OutputStream, PrintStream}

import twitter4s.net.oauth.OAuthRequest
import twitter4s.net.HttpResponse
import twitter4s.core.APIKeys

import scala.collection.mutable


/**
  * Created by lrf141 on 17/08/26.
  * @author lrf141
  * @since 1.0.0
  */
object HttpRequest {

  private [this] val endpointBaseURL:String = "https://api.twitter.com/1.1/"
  private [this] val encodeType:String = "UTF-8"

  private [this] val POST:String = "POST"
  private [this] val GET:String = "GET"

  /**
    * send GET request using http request
    * @param uri this uri append end point base url
    * @return response data:HttpResponse object
    */
  def get(uri:String):HttpResponse = {

    //make http object
    val urlObject:URL = new URL(this.endpointBaseURL+uri)
    var httpObject:HttpURLConnection = urlObject.openConnection.asInstanceOf[HttpURLConnection]

    //setting method
    httpObject.setRequestMethod(this.GET)

    val api:Map[String,String] = APIKeys.getKeysAsMap
    var paramMap:mutable.SortedMap[String,String] = OAuthRequest.getOAuthParamMap(api("consumer key"),api("access token"))

    val signature:String = OAuthRequest.makeSignature(api("consumer key"), api("access token"), this.GET, paramMap)
    paramMap += ("oauth_signature" -> signature)

    val authHeader:String = OAuthRequest.makeAuthorizationHeader(paramMap)

    //setting http request header
    httpObject.setRequestProperty("Authorization",s"${authHeader}")

    httpObject.connect

    getResponse(httpObject)


  }


  /**
    * @param uri
    * @param requestHeader
    * @return
    */
  def post(uri:String, requestHeader:Map[String,String]):HttpResponse = {

    //make http object
    val urlObject:URL = new URL(this.endpointBaseURL+uri)
    val httpObject:HttpURLConnection = urlObject.openConnection.asInstanceOf[HttpURLConnection]

    //setting method
    httpObject.setRequestMethod(this.POST)

    val api:Map[String,String] = APIKeys.getKeysAsMap
    var paramMap:mutable.SortedMap[String,String] = OAuthRequest.getOAuthParamMap(api("consumer key"),api("access token"))

    val signature:String = OAuthRequest.makeSignature(api("consumer key"), api("access token"), this.POST, paramMap)
    paramMap += ("oauth_signature" -> signature)

    val authHeader:String = OAuthRequest.makeAuthorizationHeader(paramMap)

    //setting http request header
    httpObject.setRequestProperty("Authorization",s"${authHeader}")

    httpObject.connect

    getResponse(httpObject)

  }

  /**
    * get http request response
    * @param connection HttpURLConnection Object
    * @return
    */
  def getResponse(connection: HttpURLConnection):HttpResponse = {

    val httpResponse:HttpResponse = new HttpResponse

    //get status code
    httpResponse.setStatusCode(connection.getResponseCode)

    //get response header
    val headerMap:java.util.Map[String,java.util.List[String]] = connection.getHeaderFields

    var headerMapAsScala:mutable.Map[String,List[String]] = Map.empty[String,List[String]]
    for(keys <- headerMap.keySet.asScala.toList)
      headerMapAsScala += keys -> headerMap.get(keys).asScala.toList

    val resultMap:Map[String,String] = convertHeader(headerMapAsScala)
    httpResponse.setResponseHeader(resultMap)

    //get response body
    val inputStream:InputStream = connection.getInputStream
    val reader:BufferedReader = new BufferedReader(new InputStreamReader(inputStream,this.encodeType))
    val responseBody:String = convertBody(reader)
    httpResponse.setResponseBody(responseBody)

    httpResponse
  }

  /**
    * when we get the http header, this type Map[String,List[String].
    * it convert to Map[String,String]
    * @param rawHeaderMap raw header
    * @return converted header as Map[String,String]
    */
  private def convertHeader(rawHeaderMap: Map[String,List[String]]):Map[String,String] = {

    var resultMap:Map[String,String] = Map.empty[String,String]

    for( keys <- rawHeaderMap.keys ){
      val sb:StringBuilder = new StringBuilder
      if(keys != null){
        for(value <- rawHeaderMap(keys)){
          if( 0 < sb.length )
            sb.append("\n")

          sb.append(value)
        }
        resultMap += keys -> sb.toString
      }
    }
    resultMap
  }

  /**
    * when we get response header, we get it as Buffer.
    * it data convert to String
    * @param reader body contents buffer
    * @return response body as String data
    */
  private def convertBody(reader:BufferedReader):String = {

    val body:StringBuilder = new StringBuilder
    var str:String = ""

    while({ str = reader.readLine() ; str != null}){
      body.append(str)
      body.append("\n")
    }
    body.toString
  }

  /**
    *
    * @param requestHeaderMap
    * @param header
    * @return
    */
  def makeRequestHeader(header:HttpURLConnection, requestHeaderMap:Map[String,String]):HttpURLConnection = {
    val httpRequestHeader:HttpURLConnection = header
    for(keys <- requestHeaderMap.keys)
      httpRequestHeader.setRequestProperty(keys, requestHeaderMap(keys))
    httpRequestHeader
  }
}
