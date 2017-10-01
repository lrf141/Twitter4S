package twitter4s

import scala.collection.mutable.Map

/**
  * Created by lrf141 on 17/08/29.
  * @since 1.0.0
  * @author lrf141
  */
class APIKeys {

  private [this] var ck:String = null
  private [this] var cs:String = null
  private [this] var at:String = null
  private [this] var as:String = null

  /**
    * set all api keys at the private
    * @param _ck consumer key
    * @param _cs consumer secret key
    * @param _at access token
    * @param _as access token secret
    */
  def setKeys(_ck:String, _cs:String, _at:String, _as:String):Unit = {
    this.ck = _ck
    this.cs = _cs
    this.at = _at
    this.as = _as
  }

  /**
    * return all api keys
    * @return api keys as List[String]
    */
  def getKeysAsList:List[String] = List(this.ck, this.cs,this.at,this.as)

  /**
    * @return Map[String,String]
    */
  def getKeysAsMap:Map[String,String] = {

    var keyMap:Map[String,String] = Map.empty[String,String]
    keyMap += "consumer key" -> this.ck
    keyMap += "consumer secret" -> this.cs
    keyMap += "access token" -> this.at
    keyMap += "access secret" -> this.as

    keyMap
  }

}
