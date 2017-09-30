package twitter4s

/**
  * Created by lrf141 on 17/09/28.
  */
trait TimeLineBase {

  /**
    * @return your home timeline
    */
  def getHomeTimeLine: Seq[HomeTimeLine]


  /**
    * @return
    */
  def getUserTimeLine(userName: String): Seq[UserTimeLine]

}
