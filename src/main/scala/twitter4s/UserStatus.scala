package twitter4s

/**
  * Created by lrf141 on 17/09/23.
  */


/**
  * @param name user name
  * @param screen_name user screen name
  * @param id_str user id as String
  */
case class UserStatus(name: String,
                      screen_name: String,
                      id_str: String
                     )