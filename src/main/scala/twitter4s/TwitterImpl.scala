package twitter4s

import twitter4s.net.HttpRequest
import twitter4s.net.oauth.OAuthRequest
import twitter4s.util.JsonDecoder

import scala.collection.mutable

/**
  * Created by lrf141 on 17/09/25.
  * @since 1.0.0
  * @author lrf141
  */
class TwitterImpl extends Twitter{

  private [this] val apiKeys:APIKeys = new APIKeys
  private [this] var httpRequest:HttpRequest = null

  /**
    * post your tweet on twitter by update status
    * @param tweet your tweet as String
    */
  override def updateStatus(tweet: String):Status = {

    //tweet data is up to 140 chars
    if(140 < tweet.length)
      throw new Exception

    val uri:String = "statuses/update.json"

    val requestParam:mutable.TreeMap[String,String] = mutable.TreeMap.empty[String,String]
    //included +, return 401
    requestParam += "status" -> OAuthRequest.getUrlEncode(tweet).replace("+","%20")

    val responseJson: String = httpRequest.post(uri,requestParam)
    JsonDecoder.decodeTweetStatus(responseJson)
  }

  /**
    * get your home timeline and show on terminal
    * @return get home timeline as Seq[HomeTimeLine]
    */
  override def getHomeTimeLine: Seq[HomeTimeLine] = {

    val uri:String = "statuses/home_timeline.json"

    //get as Json
    val result:String = httpRequest.get(uri,mutable.TreeMap.empty[String,String])

    //parse tweet status
    val homeTimeLine = JsonDecoder.decodeHomeTimeLine(result)

    homeTimeLine.foreach{tweet =>

      //append tweet status
      val status:StringBuilder = new StringBuilder
      status.append(tweet.user.name +" @"+ tweet.user.screen_name)
      status.append(tweet.text + ": " + tweet.created_at)

      println(status.toString)
    }

    homeTimeLine
  }

  /**
    * @return get User timeline as Seq[UserTimeLine]
    */
  override def getUserTimeLine(userName: String): Seq[UserTimeLine] = {

    val uri: String = "statuses/user_timeline.json"

    //get as Json
    val result:String = httpRequest.get(uri,mutable.TreeMap("screen_name" -> userName))

    //parse tweet status
    val userTimeLine = JsonDecoder.decodeUserTimeLine(result)
    userTimeLine.foreach{ tweet =>

      //append tweet status
      val status:StringBuilder = new StringBuilder
      status.append(tweet.text + ": " + tweet.created_at)

      println(status.toString)

    }

    userTimeLine
  }

  /**
    * get your followers list
    * @return followers list as Seq[UserStatus]
    */
  override def getFollowersList:Seq[UserStatus] = {

    val uri:String = "followers/list.json"
    val response_json:String = httpRequest.get(uri,mutable.TreeMap("cursor" -> "-1"))
    JsonDecoder.decodeUserArray(response_json)

  }

  /**
    * get friends(follow) user list as Seq[UserStatus]
    * @return get follow list as Seq[UserStatus]
    */
  override def getFriendsList:Seq[UserStatus] = {

    val uri:String = "friends/list.json"
    val response_json:String = httpRequest.get(uri,mutable.TreeMap("cursor" -> "-1"))
    JsonDecoder.decodeUserArray(response_json)
  }


  /**
    * @param q question key words
    * @return result of tweet search
    */
  override def searchTweet(q: String): Tweets = {

    val uri: String = "search/tweets.json"
    val response_json: String = httpRequest.get(uri,mutable.TreeMap("q" -> q))
    JsonDecoder.decodeTweetSearch(response_json)
  }


  /**
    * @param q question key words
    * @return result of user search
    */
  override def searchUser(q: String): Seq[UserStatus] = {

    val uri: String = "users/search.json"
    val response_json: String = httpRequest.get(uri, mutable.TreeMap("q" -> q))
    JsonDecoder.decodeUserSearch(response_json)
  }

  /**
    * @param screen_name screen_name key words
    * @return result of fav list
    */
  override def getFavoriteList(screen_name: String): Seq[Favorites] = {
    val uri: String = "favorites/list.json"
    val response_json: String = httpRequest.get(uri, mutable.TreeMap("screen_name" -> screen_name))
    JsonDecoder.decodeFavoriteList(response_json)
  }

  /**
    * @param tweet_ids wanna liked tweet ids
    * @return response json
    */
  override def createFavorite(tweet_ids: String): Tweet = {
    val uri: String = "favorites/create.json"
    val response_json: String = httpRequest.post(uri, mutable.TreeMap("id" -> tweet_ids))
    JsonDecoder.decodeTweet(response_json)
  }

  /**
    * @param tweet_ids wanna destroy liked tweet ids
    * @return response json
    */
  override def destroyFavorite(tweet_ids: String): Tweet = {
    val uri: String = "favorites/destroy.json"
    val response_json: String = httpRequest.post(uri, mutable.TreeMap("id" -> tweet_ids))
    JsonDecoder.decodeTweet(response_json)
  }

  /**
    * @param screen_name wanna create friendships by screen_name
    * @return response user status
    */
  override def createFriendshipsByName(screen_name: String): UserStatus = {
    val uri: String = "friendships/create.json"
    val response_json: String = httpRequest.post(uri, mutable.TreeMap("screen_name" -> screen_name))
    JsonDecoder.decodeUser(response_json)
  }

  /**
    * @param user_ids wanna create friendships by user_ids
    * @return response user status
    */
  override def createFriendshipsByIds(user_ids: String): UserStatus = {
    val uri: String = "friendships/create.json"
    val response_json: String = httpRequest.post(uri, mutable.TreeMap("user_id" -> user_ids))
    JsonDecoder.decodeUser(response_json)
  }


  /**
    * @param _ck consumer key
    * @param _cs consumer secret key
    * @param _at access token
    * @param _as access token secret
    */
  override def initialize(_ck: String, _cs: String, _at: String, _as: String):Unit = {
    this.apiKeys.setKeys(_ck,_cs,_at,_as)
    this.httpRequest = new HttpRequest(this.apiKeys)
  }

}
