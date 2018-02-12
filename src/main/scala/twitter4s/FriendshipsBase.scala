package twitter4s

/**
  * Created by lrf141 on 18/02/13.
  */
trait FriendshipsBase {

  def createFriendshipsByName(screen_name: String): UserStatus
  def createFriendshipsByIds(user_ids: String): UserStatus

  def destroyFriendshipsByName(screen_name: String): UserStatus
  def destroyFriendshipsByIds(user_ids: String): UserStatus


}
