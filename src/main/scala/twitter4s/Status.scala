package twitter4s

/**
  * Created by lrf141 on 17/09/26.
  * @since 1.0.0
  * @author lrf141
  *
  * @param text tweet
  * @param created_at
  * @param id_str tweet id as String
  */
case class Status(text: String,
                       created_at: String,
                       id_str: String)
