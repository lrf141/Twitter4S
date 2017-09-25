package twitter4s

/**
  * Created by lrf141 on 17/09/26.
  */
class TwitterFactory extends TwitterImpl{

}


object TwitterFactory{

  private [this] val instance:Twitter = new TwitterImpl

  def getInstance: Twitter = this.instance

  def apply: Twitter = getInstance

}