package twitter4s

/**
  * Created by lrf141 on 17/09/26.
  */
trait Status {

  /**
    * @param tweetText
    * @return
    */
  def updateStatus(tweetText: String): TweetStatus
}