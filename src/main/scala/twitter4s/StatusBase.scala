package twitter4s

/**
  * Created by rabbitfoot on 17/09/27.
  */
trait StatusBase {

  /**
    * @param text
    * @return
    */
  def updateStatus(text: String): Status

}
