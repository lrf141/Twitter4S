package twitter4s

/**
  * Created by lrf141 on 17/09/26.
  */
class TwitterFactory private extends TwitterImpl


object TwitterFactory{

  private [this] val instance:Twitter = new TwitterImpl

  /**
    * @return
    */
  def getInstance: Twitter = this.instance

  /**
    * @return
    */
  def apply: Twitter = getInstance

}