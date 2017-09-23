package twitter4s


/**
  * parse result contains this case class
  * Created by lrf141 on 17/09/23.
  */

/**
  * @param created_at tweet made time
  * @param id_str tweet id as String
  * @param text tweet text
  * @param id tweet id as Long
  * @param retweet_count
  * @param user user object in response json
  */
case class UserTimeLine(created_at: String,
                        id_str: String,
                        text: String,
                        id: Long,
                        retweet_count: Int,
                        user: UserStatus
                       )