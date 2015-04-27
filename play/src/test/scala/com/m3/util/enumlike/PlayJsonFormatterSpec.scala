package com.m3.util.enumlike

import org.scalatest.{ FlatSpec, Matchers }
import play.api.libs.json.{ JsString, JsSuccess, Json }

class PlayJsonFormatterSpec extends FlatSpec with Matchers with PlayJsonFormatter {

  sealed trait Foo extends StringEnumLike
  object Foo extends EnumCompanion[Foo] {

    case object A extends Foo
    case object B extends Foo
    case object C extends Foo

    val values = findValues

  }

  behavior of "JSON serialization"

  it should "serialize to a JSON string" in {
    Json.toJson[Foo](Foo.A) should be(JsString("A"))
  }

  it should "deserialize from a JSON string" in {
    Json.fromJson[Foo](JsString("B")) should be(JsSuccess(Foo.B))
  }

}
