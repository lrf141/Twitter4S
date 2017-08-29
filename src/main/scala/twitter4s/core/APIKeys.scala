package twitter4s.core

import scala.collection.mutable.Map

/**
  * Created by lrf141 on 17/08/29.
  */
object APIKeys {

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
    * set consumer key and consumer secret key when you use
    * OAuth1.0 Authentication
    * @param _ck
    * @param _cs
    */
  def setKeys(_ck:String, _cs:String):Unit = {
    this.ck = _ck
    this.cs = _cs
  }

  /**
    * return all api keys
    * @return api keys as List[String]
    */
  def getKeysAsList:List[String] = {
    if(this.at == null || this.as == null)
      List(this.ck, this.cs, this.at, this.as)
    else
      List(this.ck, this.cs)
  }

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
