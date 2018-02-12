package twitter4s

/**
  * Created by lrf141 on 17/10/17.
  *
  * @author lrf141
  */
trait FavoriteBase {

  def getFavoriteList(screen_name: String):Seq[Favorites]

  def createFavorite(tweet_ids: String): Tweet

  def destroyFavorite(tweet_ids: String): Tweet
}
