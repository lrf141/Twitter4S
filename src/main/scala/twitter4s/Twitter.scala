package twitter4s

/**
  * Created by lrf141 on 17/09/25.
  */
trait Twitter {

  /**
    * @param tweet post data which length is up to 140 char
    */
  def updateStatus(tweet: String):TweetStatus

  /**
    * @return your home timeline
    */
  def getHomeTimeLine: Seq[UserTimeLine]

  /**
    * @return your followers list as Seq
    */
  def getFollowersList: Seq[UserStatus]

  /**
    * @return your friends (Follow) list as Seq
    */
  def getFriendsList: Seq[UserStatus]

  /**
    * @param _ck consumer key
    * @param _cs consumer secret key
    * @param _at access token
    * @param _as access token secret
    */
  def initialize(_ck: String, _cs: String, _at: String, _as: String):Unit

}
