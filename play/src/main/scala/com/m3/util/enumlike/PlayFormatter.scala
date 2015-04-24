package com.m3.util.enumlike

import play.api.data.FormError
import play.api.data.format.Formatter
import play.api.mvc.{ JavascriptLitteral, QueryStringBindable, PathBindable }

import scala.reflect.ClassTag

/**
 * Use this when you do not want your enums to depend on play, but still have the formatters
 */
trait PlayFormatter {

  implicit def enumLikeFormatter[E <: EnumLike](implicit ev: EnumCompanion[E], base: Formatter[E#ValueType], tag: ClassTag[E]): Formatter[E] = new Formatter[E] {
    def bind(key: String, data: Map[String, String]): Either[Seq[FormError], E] = {
      base.bind(key, data) match {
        case Right(v) => ev.valueOf(v) match {
          case None => Left(Seq(FormError(key, "error.invalid.enum", Seq(tag.runtimeClass.getName))))
          case Some(e) => Right(e)
        }
        case Left(errors) => Left(errors)
      }
    }

    def unbind(key: String, value: E): Map[String, String] = base.unbind(key, value.value)
  }

  /**
   * Given an enum like sealed objects, returns a [[PathBindable]], which is useful for binding and
   * unbinding path params from routes
   */
  implicit def enumLikePathBindable[E <: EnumLike](implicit ev: EnumCompanion[E], base: PathBindable[E#ValueType]): PathBindable[E] = new PathBindable[E] {
    def bind(key: String, value: String) = {
      for {
        v <- base.bind(key, value).right
        a <- ev.valueOf(v).toRight(s"$key should have one of the following values: ${ev.values.mkString("[", ", ", "]")}").right
      } yield a
    }

    def unbind(key: String, value: E) = base.unbind(key, value.value)
  }

  /**
   * Given an enum like sealed objects, returns a [[QueryStringBindable]], which is useful for binding and
   * unbinding query string params from routes
   */
  implicit def enumLikeQueryStringBindable[E <: EnumLike](implicit ev: EnumCompanion[E], base: QueryStringBindable[E#ValueType]): QueryStringBindable[E] = new QueryStringBindable[E] {
    def bind(key: String, params: Map[String, Seq[String]]) = {
      base.bind(key, params).map {
        _.right.flatMap { value =>
          ev.valueOf(value).toRight(s"$key should have one of the following values: ${ev.values.mkString("[", ", ", "]")}")
        }
      }
    }

    def unbind(key: String, value: E) = base.unbind(key, value.value)
  }

  // This is sometimes used by reverse routing, when routes may conflict.
  // Beware, this performs no escaping, so for enums with a string value avoid single quotes
  implicit def enumLikeJavascriptLitteral[E <: EnumLike](implicit ev: EnumCompanion[E], base: JavascriptLitteral[E#ValueType]): JavascriptLitteral[E] = new JavascriptLitteral[E] {
    def to(a: E) = base.to(a.value)
  }

}

object PlayFormatter extends PlayFormatter
