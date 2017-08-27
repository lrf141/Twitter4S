package twitter4s.util

import java.util.Base64

/**
  * Created by lrf141 on 17/08/27.
  * @since 1.0.0
  */
object Base64Translator {


  /**
    * @param base
    * @return encoded string value
    */
  def encode(base:String):String = {
    Base64.getEncoder.encodeToString(base.getBytes)
  }

  /**
    * @param text
    * @return decoded string value
    */
  def decode(text:String):String = {
    new String(Base64.getDecoder.decode(text))
  }

}
