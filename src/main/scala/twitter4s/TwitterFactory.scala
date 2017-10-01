package twitter4s

/**
  * Created by lrf141 on 17/09/26.
  * @since 1.0.0
  * @author lrf141
  */
class TwitterFactory private extends TwitterImpl


object TwitterFactory{

  private [this] val instance:Twitter = new TwitterImpl

  /**
    * @return this instance
    */
  def getInstance: Twitter = this.instance

  /**
    * @return call getInstance Method
    */
  def apply: Twitter = getInstance

}