import org.scalatest.Assertions
import org.scalatest.FunSpec
import org.scalatest.Matchers._
import twitter4s.core.Twitter4s


/**
  * Created by lrf141 on 17/08/27.
  */
class TLTest extends FunSpec{

  describe("base64 unit test"){

    it("tweet test"){

      val twitter:Twitter4s = new Twitter4s()
      twitter.setAPIKeys(
        "p1KlueuWAbjPgaaEMdLwr3LYI",
        "ELoJmDhabX0wJqo8ife2OovnQmmMheTElBBe1FnhQhRs1Bvlra",
        "1731830389-lAzQuxGYLnhmDguu5jVjE2X0dd8y17CThvmWa9Y",
        "hW0TPzCDAbEdQA82xEi4gh2Bcy2STYZ8hI8cjJhdqOm8z"
      )


      val res = twitter.getHomeTimeLine
      println(res)

    }

  }

}
