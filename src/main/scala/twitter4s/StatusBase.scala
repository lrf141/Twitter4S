package twitter4s

/**
  * Created by lrf141 on 17/09/27.
  * @since 1.0.0
  * @author lrf141
  */
trait StatusBase {

  /**
    * @param text
    * @return response json as Status
    */
  def updateStatus(text: String): Status

}
