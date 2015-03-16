package com.m3.util.enumlike

import com.m3.util.enumlike.Beatle._
import org.scalatest.{ FlatSpec, Matchers }
import play.api.mvc.{ PathBindable, QueryStringBindable }

class CustomBinderBuildersSpec extends FlatSpec with Matchers with CustomBinderBuilders {

  behavior of "#enumLikePathBindable"

  val beatlePathBindable = implicitly[PathBindable[Beatle]]

  it should "bind a known String" in {
    beatlePathBindable.bind("name", "Ringo") should be(Right(Ringo))
  }

  it should "fail to bind an unknown String" in {
    beatlePathBindable.bind("name", "Chris") should be('left)
  }

  it should "unbind" in {
    beatlePathBindable.unbind("name", Ringo) should be("Ringo")
  }

  behavior of "#enumLikeQueryStringBindable"

  val beatleQueryStringBindable = implicitly[QueryStringBindable[Beatle]]

  it should "bind a known String" in {
    beatleQueryStringBindable.bind("name", Map("foo" -> Seq("bar"), "name" -> Seq("Ringo"))) should be(Some(Right(Ringo)))
  }

  it should "fail to bind an unknown String" in {
    beatleQueryStringBindable.bind("name", Map("foo" -> Seq("bar"), "name" -> Seq("Chris"))).get should be('left)
  }

  it should "not bind if param is not present" in {
    beatleQueryStringBindable.bind("name", Map("foo" -> Seq("bar"))) should be(None)
  }

  it should "unbind" in {
    beatleQueryStringBindable.unbind("name", Ringo) should be("name=Ringo")
  }

}

