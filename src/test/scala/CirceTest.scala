import io.circe.parser._
import io.circe.generic.auto._
import org.scalatest.FunSpec
import org.scalatest.Matchers._

/**
  * Created by lrf141 on 17/09/21.
  */
class CirceTest extends FunSpec{

  describe("simple json parse"){


    it("simple 1"){
      case class User(name: String, age: Int)

      val res = decode[User]("""{"name": "lrf141", "age": 20}""") match {
        case Right(person) => true
        case Left(error) => false
      }

      res should be (true)

    }

    it("simple 2"){
      case class Lang(lang1: String, prog: String)
      case class Status(age: Int, star: String)
      case class User(name: String, status: Seq[Status])

      val res = decode[User](""" {"name": "lrf141", "status": [{"age": 20, "star": "okay"}] } """) match {
        case Right(user) => true
        case Left(error) => false
      }

      res should be (true)
    }

    it("simple 3"){
      case class Status(hobby: String, lang: String)
      case class User(name: String, age:Int, status: Seq[Status])


      val res = decode[User](""" { "name": "lrf141", "age": 20, "status": { "hobby": "programming", "lang": "Scala" } } """) match {
        case Right(user) => true
        case Left(error) => false
      }

      res should be (true)
    }

    it("simple 4"){

      case class User(name: String)

      val res = decode[User]("""{"name": "lrf141", "age": 20}""") match {
        case Right(user) => true
        case Left(error) => false
      }

      res should be (true)

    }


  }

}
