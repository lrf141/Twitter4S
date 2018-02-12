package twitter4s

/**
  * Created by lrf141 on 17/10/17.
  *
  * @author lrf141
  */
trait FavoriteBase {

  def getFavoriteList(q: String):Seq[Favorites]

}
