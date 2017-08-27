package twitter4s.core

/**
  * Created by lrf141 on 17/08/26.
  * twitter4s is a Scala wrapper for the Twitter API.
  * this lib is using REST API
  * @author lrf141
  * @since 1.0.0
  */
class twitter4s {

  /**
    * Twitter API Keys managed by this object
    * @author lrf141
    */
  object Keys{

    private [this] var ck:String = null
    private [this] var cs:String = null
    private [this] var ak:String = null
    private [this] var as:String = null

    /**
      * set all api keys at the private
      * @param _ck consumer key
      * @param _cs consumer secret key
      * @param _ak access token
      * @param _as access token secret
      */
    def setKeys(_ck:String, _cs:String, _ak:String, _as:String):Unit = {
      this.ck = _ck
      this.cs = _cs
      this.ak = _ak
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
      if(this.ak == null || this.as == null)
        List(this.ck, this.cs, this.ak, this.as)
      else
        List(this.ck, this.cs)
    }
  }

}
