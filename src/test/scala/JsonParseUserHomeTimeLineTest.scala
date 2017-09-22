import org.scalatest.FunSpec
import org.scalatest.Matchers._
import twitter4s.core.{HomeTimeLine, MinTimeLineData, MinUser}
import io.circe._
import io.circe.generic.auto._
import io.circe.Decoder
import io.circe.parser._


/**
  * Created by lrf141 on 17/09/21.
  */
class JsonParseUserHomeTimeLineTest extends FunSpec{

  describe("json parse test"){

    case class HomeTL()


    it("user 1"){
      val p = parse("""[
                                      {
                                        "coordinates": null,
                                        "truncated": false,
                                        "created_at": "Tue Aug 28 21:16:23 +0000 2012",
                                        "favorited": false,
                                        "id_str": "240558470661799936",
                                        "in_reply_to_user_id_str": null,
                                        "entities": {
                                          "urls": [

                                          ],
                                          "hashtags": [

                                          ],
                                          "user_mentions": [

                                          ]
                                        },
                                        "text": "just another test",
                                        "contributors": null,
                                        "id": 240558470661799936,
                                        "retweet_count": 0,
                                        "in_reply_to_status_id_str": null,
                                        "geo": null,
                                        "retweeted": false,
                                        "in_reply_to_user_id": null,
                                        "place": null,
                                        "source": "OAuth Dancer Reborn",
                                        "user": {
                                          "name": "OAuth Dancer",
                                          "profile_sidebar_fill_color": "DDEEF6",
                                          "profile_background_tile": true,
                                          "profile_sidebar_border_color": "C0DEED",
                                          "profile_image_url": "http://a0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
                                          "created_at": "Wed Mar 03 19:37:35 +0000 2010",
                                          "location": "San Francisco, CA",
                                         "follow_request_sent": false,
                                          "id_str": "119476949",
                                          "is_translator": false,
                                          "profile_link_color": "0084B4",
                                          "entities": {
                                            "url": {
                                              "urls": [
                                                {
                                                  "expanded_url": null,
                                                  "url": "http://bit.ly/oauth-dancer",
                                                  "indices": [
                                                   0,
                                                    26
                                                  ],
                                                  "display_url": null
                                                }
                                              ]
                                            },
                                            "description": null
                                          },
                                          "default_profile": false,
                                          "url": "http://bit.ly/oauth-dancer",
                                          "contributors_enabled": false,
                                          "favourites_count": 7,
                                          "utc_offset": null,
                                          "profile_image_url_https": "https://si0.twimg.com/profile_images/730275945/oauth-dancer_normal.jpg",
                                          "id": 119476949,
                                         "listed_count": 1,
                                          "profile_use_background_image": true,
                                         "profile_text_color": "333333",
                                          "followers_count": 28,
                                          "lang": "en",
                                          "protected": false,
                                          "geo_enabled": true,
                                          "notifications": false,
                                          "description": "",
                                          "profile_background_color": "C0DEED",
                                          "verified": false,
                                          "time_zone": null,
                                          "profile_background_image_url_https": "https://si0.twimg.com/profile_background_images/80151733/oauth-dance.png",
                                          "statuses_count": 166,
                                          "profile_background_image_url": "http://a0.twimg.com/profile_background_images/80151733/oauth-dance.png",
                                          "default_profile_image": false,
                                          "friends_count": 14,
                                          "following": false,
                                          "show_all_inline_media": false,
                                          "screen_name": "oauth_dancer"
                                        },
                                        "in_reply_to_screen_name": null,
                                        "in_reply_to_status_id": null
                                      }
                                      ]
                                  """).flatMap(_.as[Array[MinTimeLineData]]) match {
        case Right(data) => {
          println(data)
          true
        }
        case Left(error) => {
          println(error)
          false
        }
      }


      p should be (true)


    }
  }

}
