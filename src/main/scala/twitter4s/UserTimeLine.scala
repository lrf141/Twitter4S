package twitter4s

/**
  * Created by lrf141 on 17/09/27.
  * @author lrf141
  * @since 1.0.0
  *
  * @param created_at
  * @param id_str
  * @param text
  */
case class UserTimeLine(created_at: String,
                        id_str: String,
                        text: String)