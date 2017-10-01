package twitter4s

/**
  * Created by lrf141 on 17/09/28.
  * @since 1.0.0
  * @author lrf141
  */
trait TimeLineBase {

  /**
    * @return your home timeline
    */
  def getHomeTimeLine: Seq[HomeTimeLine]


  /**
    * @return user time line
    */
  def getUserTimeLine(userName: String): Seq[UserTimeLine]

}
