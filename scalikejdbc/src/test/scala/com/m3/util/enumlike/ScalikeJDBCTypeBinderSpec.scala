package com.m3.util.enumlike

import com.m3.util.enumlike.Foo._
import org.scalatest.{ Matchers, FlatSpec }
import scalikejdbc._

class ScalikeJDBCTypeBinderSpec extends FlatSpec with Matchers {

  behavior of "TypeBinder"

  it should "find an instance implicitly" in {
    "implicitly[TypeBinder[Foo]]" should compile
  }

  it should "find an optional instance implicitly" in {
    "implicitly[TypeBinder[Option[Foo]]]" should compile
  }

}
