import io.circe.parser._
import io.circe.generic.auto._
import org.scalatest.FunSpec
import org.scalatest.Matchers._
import twitter4s.util.JsonDecoder

/**
  * Created by lrf141 on 18/02/13.
  */
class TweetParseTest extends FunSpec{

  describe("tweet parse test"){

    it("simple tweet parse test"){
      val json: String =
        """
          |{
          |  "contributors": null,
          |  "coordinates": null,
          |  "created_at": "Wed Sep 05 00:07:01 +0000 2012",
          |  "entities": {
          |    "hashtags": [],
          |    "urls": [],
          |    "user_mentions": []
          |  },
          |  "favorited": false,
          |  "geo": null,
          |  "id": 243138128959913986,
          |  "id_str": "243138128959913986",
          |  "in_reply_to_screen_name": null,
          |  "in_reply_to_status_id": null,
          |  "in_reply_to_status_id_str": null,
          |  "in_reply_to_user_id": null,
          |  "in_reply_to_user_id_str": null,
          |  "place": null,
          |  "retweet_count": 0,
          |  "retweeted": false,
          |  "source": "Twitter for Mac",
          |  "text": "That feel when you accidentally type Bash commands into Campfire, and you also make a typo in them.",
          |  "truncated": false,
          |  "user": {
          |    "contributors_enabled": false,
          |    "created_at": "Wed Apr 23 20:32:35 +0000 2008",
          |    "default_profile": false,
          |    "default_profile_image": false,
          |    "description": "Developer at GitHub in San Francisco, CA.rnrnChicken nuggets is like my family.",
          |    "entities": {
          |      "description": {
          |        "urls": []
          |      },
          |      "url": {
          |        "urls": [
          |          {
          |            "display_url": null,
          |            "expanded_url": null,
          |            "indices": [
          |              0,
          |              21
          |            ],
          |            "url": "http://jakeboxer.com/"
          |          }
          |        ]
          |      }
          |    },
          |    "favourites_count": 187,
          |    "follow_request_sent": false,
          |    "followers_count": 714,
          |    "following": false,
          |    "friends_count": 327,
          |    "geo_enabled": true,
          |    "id": 14500363,
          |    "id_str": "14500363",
          |    "is_translator": false,
          |    "lang": "en",
          |    "listed_count": 39,
          |    "location": "San Francisco, CA",
          |    "name": "Jake Boxer",
          |    "notifications": false,
          |    "profile_background_color": "352726",
          |    "profile_background_image_url": "http://a0.twimg.com/images/themes/theme5/bg.gif",
          |    "profile_background_image_url_https": "https://si0.twimg.com/images/themes/theme5/bg.gif",
          |    "profile_background_tile": false,
          |    "profile_image_url": "http://a0.twimg.com/profile_images/1621757700/twitter_normal.png",
          |    "profile_image_url_https": "https://si0.twimg.com/profile_images/1621757700/twitter_normal.png",
          |    "profile_link_color": "D02B55",
          |    "profile_sidebar_border_color": "829D5E",
          |    "profile_sidebar_fill_color": "99CC33",
          |    "profile_text_color": "3E4415",
          |    "profile_use_background_image": true,
          |    "protected": false,
          |    "screen_name": "jake_boxer",
          |    "show_all_inline_media": false,
          |    "statuses_count": 5398,
          |    "time_zone": "Eastern Time (US & Canada)",
          |    "url": "http://jakeboxer.com/",
          |    "utc_offset": -18000,
          |    "verified": false
          |  }
          |}
        """.stripMargin
      val res = JsonDecoder.decodeTweet(json)
      println(res)
    }

  }

}
