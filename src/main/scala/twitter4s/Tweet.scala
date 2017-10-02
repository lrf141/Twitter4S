package twitter4s

/**
  * Created by lrf141 on 17/10/02.
  */
case class Tweet(created_at: String,
                 id_str: String,
                 text: String,
                 user: UserStatus)
