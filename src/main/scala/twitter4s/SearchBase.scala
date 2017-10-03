package twitter4s

/**
  * Created by lrf141 on 17/09/26
  * @since 1.0.0
  * @author lrf141
  */
trait SearchBase {

  /**
    * @param q question key words
    * @return result of tweet search
    */
  def searchTweet(q: String): Tweets


  /**
    * @param q question key words
    * @return result of user search
    */
  def searchUser(q: String): Seq[UserStatus]

}
