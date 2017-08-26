package twtter4s.net

import scala.collection.mutable.Map
import java.net.{HttpURLConnection,
                 URL,
                 URLEncoder}
import java.io.{BufferedReader,
                InputStream,
                PrintStream,
                InputStreamReader,
                IOException,
                OutputStream}


/**
  * Created by lrf141 on 17/08/26.
  * @author lrf141
  * @since 0.0.1
  */
class HttpRequest {

  private [this] val endpointBaseURL:String = "https://api.twitter.com/1.1/"
  private [this] val oauthRequestURL:String = "https://api.twitter.com/oauth/request_token"
  private [this] val encodeType:String = "UTF-8"

  /**
    * send GET request using http request
    * @param uri this uri append end point base url
    * @return response data:HttpResponse object
    */
  def doGet(uri:String):HttpResponse = {

    //make http object
    val urlObject:URL = new URL(this.endpointBaseURL+uri)
    val httpObject:HttpURLConnection = urlObject.openConnection().asInstanceOf[HttpURLConnection]

    //setting method
    httpObject.setRequestMethod("GET")
    httpObject.setInstanceFollowRedirects(false)

    //starting connect
    httpObject.connect()

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
    val headerMap = connection.getHeaderFields.asInstanceOf[Map[String,List[String]]]
    val resultMap:Map[String,String] = convertHeader(headerMap)
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
  def convertHeader(rawHeaderMap: Map[String,List[String]]):Map[String,String] = {

    var resultMap:Map[String,String] = Map.empty[String,String]

    for( keys <- rawHeaderMap.keys ){
      val sb:StringBuilder = new StringBuilder
      if(keys != null){
        for(value <- rawHeaderMap(keys)){
          if( 0 < sb.length )
            sb.append("\n")

          sb.append(value)
        }
        resultMap += ((keys,sb.toString))
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
}
