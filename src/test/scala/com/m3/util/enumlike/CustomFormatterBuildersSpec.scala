package com.m3.util.enumlike

import org.scalatest.{ FunSpec, Matchers }
import play.api.data.format.Formatter

class CustomFormatterBuildersSpec extends FunSpec with Matchers with CustomFormatterBuilders {

  private sealed trait E extends StringEnumLike

  private object E extends EnumCompanion[E] {

    case object A extends E

    case object B extends E

    case object C extends E

    case object D extends E {
      override def toString = "NOT D"
    }

    val values = findValues.toIndexedSeq
  }

  describe("enumLikeFormatter") {
    import play.api.data.format.Formats.stringFormat
    val eFormatter = implicitly[Formatter[E]]
    it("should unbind as toString") {
      eFormatter.unbind("X", E.A) should be(Map("X" -> "A"))
      eFormatter.unbind("X", E.D) should be(Map("X" -> "NOT D"))
    }
    it("should bind, failing with an error message (not translated since application is not running)") {
      val bindFailures = Seq(
        eFormatter.bind("X", Map()),
        eFormatter.bind("X", Map("X" -> "D"))
      )
      bindFailures.foreach { bindResult =>
        val fe = bindResult.left.get.head
        fe.message should include("error")
      }
      eFormatter.bind("X", Map("X" -> "NOT D")).right.get should be(E.D)
    }
  }

}

