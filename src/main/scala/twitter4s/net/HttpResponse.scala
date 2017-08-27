package twitter4s.net

import scala.collection.mutable.Map

/**
  * Created by lrf141 on 17/08/26.
  */
class HttpResponse {

  private [this] var statusCode:Int = 0
  private [this] var responseHeader:Map[String,String] = Map.empty[String,String]
  private [this] var responseBody:String = null

  /**
    *
    * @param statusCode
    */
  def setStatusCode(statusCode:Int) = this.statusCode = statusCode

  /**
    *
    * @param responseHeader
    */
  def setResponseHeader(responseHeader:Map[String,String]) = this.responseHeader = responseHeader

  /**
    *
    * @param responseBody
    */
  def setResponseBody(responseBody:String) = this.responseBody = responseBody

}
