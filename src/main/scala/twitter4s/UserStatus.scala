package twitter4s

/**
  * Created by lrf141 on 17/09/23.
  * @since 1.0.0
  * @author lrf141
  *
  * @param name user name
  * @param screen_name user screen name
  * @param id_str user id as String
  * @param description profile description
  * @param followers_count followers
  * @param friends_count follows
  * @param `protected ` is protected account
  *
  */
case class UserStatus(name: String,
                      screen_name: String,
                      id_str: String,
                      description: String,
                      statuses_count: Int,
                      favourites_count: Int,
                      followers_count: Int,
                      friends_count: Int,
                      `protected `: Boolean
                     )