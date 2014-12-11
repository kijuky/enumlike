package com.m3.util.enumlike

import play.api.mvc.{ JavascriptLitteral, PathBindable, QueryStringBindable }

trait CustomBinderBuilders {

  /**
   * Given an enum like sealed objects, returns a [[PathBindable]], which is useful for binding and
   * unbinding path params from routes
   */
  implicit def enumLikePathBindable[A <: EnumLike](implicit ev: EnumCompanion[A], base: PathBindable[A#ValueType]) = new PathBindable[A] {
    def bind(key: String, value: String) = {
      for {
        v <- base.bind(key, value).right
        a <- ev.valueOf(v).toRight(s"$key should have one of the following values: ${ev.values.mkString("[", ", ", "]")}").right
      } yield a
    }

    def unbind(key: String, value: A) = base.unbind(key, value.value)
  }

  /**
   * Given an enum like sealed objects, returns a [[QueryStringBindable]], which is useful for binding and
   * unbinding query string params from routes
   */
  implicit def enumLikeQueryStringBindable[A <: EnumLike](implicit ev: EnumCompanion[A], base: QueryStringBindable[A#ValueType]) = new QueryStringBindable[A] {
    def bind(key: String, params: Map[String, Seq[String]]) = {
      base.bind(key, params).map {
        _.right.flatMap { value =>
          ev.valueOf(value).toRight(s"$key should have one of the following values: ${ev.values.mkString("[", ", ", "]")}")
        }
      }
    }

    def unbind(key: String, value: A) = base.unbind(key, value.value)
  }

  // This is sometimes used by reverse routing, when routes may conflict.
  // Beware, this performs no escaping, so for enums with a string value avoid single quotes
  implicit def enumLikeJavascriptLitteral[A <: EnumLike](implicit ev: EnumCompanion[A], base: JavascriptLitteral[A#ValueType]) = new JavascriptLitteral[A] {
    def to(a: A) = base.to(a.value)
  }

}
