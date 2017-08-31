import org.scalatest.Assertions
import org.scalatest.FunSpec
import org.scalatest.Matchers._

import twitter4s.util.Base64Translator

/**
  * Created by lrf141 on 17/08/27.
  */
class Base64Test extends FunSpec{

  describe("base64 unit test"){

    it("encode and decode match test"){
      val str:String = "Hello,World!!"

      val encode:String = Base64Translator.encode(str)
      val decode:String = Base64Translator.decode(encode)

      encode should be ("SGVsbG8sV29ybGQhIQ==")
      decode should be ("Hello,World!!")
    }

    it("encode test"){

      val str1:String = "Hello,World!!"
      val str2:String = "I Love Scala."
      val str3:String = "i wanna girl friends."

      Base64Translator.encode(str1) should be ("SGVsbG8sV29ybGQhIQ==")
      Base64Translator.encode(str2) should be ("SSBMb3ZlIFNjYWxhLg==")
      Base64Translator.encode(str3) should be ("aSB3YW5uYSBnaXJsIGZyaWVuZHMu")

    }

    it("decode test"){

      val str1:String = "SGVsbG8sV29ybGQhIQ=="
      val str2:String = "SSBMb3ZlIFNjYWxhLg=="
      val str3:String = "aSB3YW5uYSBnaXJsIGZyaWVuZHMu"

      Base64Translator.decode(str1) should be ("Hello,World!!")
      Base64Translator.decode(str2) should be ("I Love Scala.")
      Base64Translator.decode(str3) should be ("i wanna girl friends.")

    }

  }

}
