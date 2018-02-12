package twitter4s

/**
  * Created by lrf141 on 18/02/13.
  */
trait RetweetBase {

  def createRetweet(tweet_ids: String): Tweet
  def destroyRetweet(tweet_ids: String): Tweet

}
