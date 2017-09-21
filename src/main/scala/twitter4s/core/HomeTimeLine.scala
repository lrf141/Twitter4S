package twitter4s.core

/**
  * Created by rabbitfoot on 17/09/21.
  */
case class HomeTimeLine(coordinates: String,
                        truncated: Boolean,
                        created_at: String,
                        favorited: Boolean,
                        id_str: String,
                        in_reply_to_user_id_str: String,
                        entities: Seq[Entities],
                        text: String,
                        contributors: String,
                        id: String,
                        retweet_count: String,
                        //in_reply_to_user_id_str: String,
                        geo: String,
                        retweeted:Boolean,
                        in_reply_to_user_id: String,
                        place: String,
                        source: String,
                        user: Seq[User]
                       )

case class Entities(urls:String, hashtags:String, user_mentions:String)

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
                entities: Seq[Entities])