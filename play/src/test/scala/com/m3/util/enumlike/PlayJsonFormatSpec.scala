package com.m3.util.enumlike

import com.m3.util.enumlike.Foo._
import org.scalatest.{ Matchers, FlatSpec }
import play.api.libs.json.{ JsSuccess, JsString, Json }

class PlayJsonFormatSpec extends FlatSpec with Matchers {

  behavior of "JSON serialization"

  it should "serialize to a JSON string" in {
    Json.toJson(Aaa) should be(JsString("Aaa"))
  }

  it should "deserialize from a JSON string" in {
    Json.fromJson(JsString("Bbb")) should be(JsSuccess(Bbb))
  }

}
