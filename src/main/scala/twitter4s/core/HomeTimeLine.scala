package twitter4s.core

/**
  * Created by lrf141 on 17/09/21.
  */

/**
  * @param coordinates
  * @param truncated
  * @param created_at
  * @param favorited
  * @param id_str
  * @param in_reply_to_user_id_str
  * @param text
  * @param contributors
  * @param id
  * @param retweet_count
  * @param geo
  * @param retweeted
  * @param in_reply_to_user_id
  * @param place
  * @param source
  * @param in_reply_to_screen_name
  * @param in_reply_to_status_id
  */
case class HomeTimeLine(coordinates: String,
                        truncated: Boolean,
                        created_at: String,
                        favorited: Boolean,
                        id_str: String,
                        in_reply_to_user_id_str: String,
                        text: String,
                        contributors: String,
                        id: String,
                        retweet_count: String,
                        //in_reply_to_user_id_str: String,
                        geo: String,
                        retweeted: Boolean,
                        in_reply_to_user_id: String,
                        place: String,
                        source: String,
                        in_reply_to_screen_name: String,
                        in_reply_to_status_id: String
                       )

/**
  *
  * @param created_at
  * @param id_str
  * @param text
  * @param id
  * @param retweet_count
  */
case class MinTimeLineData(created_at: String,
                           id_str: String,
                           text: String,
                           id: Long,
                           retweet_count: Int,
                           user: MinUser)

/**
  * @param urls
  * @param hashtags
  * @param user_mentions
  */
case class EntitiesUrls(urls: Seq[URLS] = null,
                    hashtags: List[String] = null,
                    user_mentions: List[String] = null
                   )

/**
  * @param expanded_url
  * @param url
  * @param indices
  * @param display_url
  */
case class URLS(expanded_url: String,
                url: String,
                indices: List[Int],
                display_url: String)

/**
  * @param name
  * @param profile_sidebar_fill_color
  * @param profile_background_tile
  * @param profile_sidebar_border_color
  * @param profile_image_url
  * @param created_at
  * @param location
  * @param follow_request_sent
  * @param id_str
  * @param is_translator
  * @param profile_link_color
  * @param entities
  * @param default_profile
  * @param url
  * @param contributors_enabled
  * @param favourites_count
  * @param utc_offset
  * @param profile_image_url_https
  * @param id
  * @param listed_count
  * @param profile_use_background_image
  * @param profile_text_color
  * @param followers_count
  * @param lang
  * @param geo_enabled
  * @param notifications
  * @param description
  * @param profile_background_color
  * @param verified
  * @param time_zone
  * @param statuses_count
  * @param profile_background_image_url
  * @param profile_background_image
  * @param default_profile_image
  * @param friends_count
  * @param following
  * @param show_all_inline_media
  * @param screen_name
  */
case class User(name: String,
                profile_sidebar_fill_color: String,
                profile_background_tile: Boolean,
                profile_sidebar_border_color: String,
                profile_image_url: String,
                created_at: String,
                location: String,
                follow_request_sent: String,
                id_str: String,
                is_translator: Boolean,
                profile_link_color: Boolean,
                entities: Seq[EntitiesUrls],
                default_profile: Boolean,
                url: String,
                contributors_enabled: Boolean,
                favourites_count: Long,
                utc_offset: String,
                profile_image_url_https: String,
                id: String,
                listed_count: Long,
                profile_use_background_image: Boolean,
                profile_text_color: String,
                followers_count: Long,
                lang: String,
                geo_enabled: Boolean,
                notifications: Boolean,
                description: String,
                profile_background_color: String,
                verified: Boolean,
                time_zone: String,
                `protected`: Boolean,
                statuses_count: Long,
                profile_background_image_url: String,
                profile_background_image: Boolean,
                default_profile_image: Boolean,
                friends_count: Long,
                following: Boolean,
                show_all_inline_media: Boolean,
                screen_name: String
               )


/**
  * @param name
  * @param screen_name
  */
case class MinUser(name: String,
                   screen_name: String
                  )