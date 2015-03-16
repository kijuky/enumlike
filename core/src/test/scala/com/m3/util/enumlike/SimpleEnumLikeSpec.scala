package com.m3.util.enumlike

import org.scalatest._

class SimpleEnumLikeSpec extends FunSpec with Matchers {

  describe("#toString") {

    it("should not surprise the developer") {
      sealed abstract class SourceType(v: String) extends SimpleEnumLike(v)
      case object Academy extends SourceType("ACADEMY")
      Academy.toString should be("ACADEMY")
    }

  }

}
