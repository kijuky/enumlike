package com.m3.util.enumlike.bigtest

import org.scalatest.{ FunSpec, Matchers }
import play.api.libs.json._

class CrashTest extends FunSpec with Matchers with Formats {
  it("should crash!") {
    val me = Driver("Vincent", CarMaker.Honda)
    implicitly[Writes[Car]].writes(Car(5, CarMaker.Toyota, Some(me))).toString() should include("Vincent")
  }
}
