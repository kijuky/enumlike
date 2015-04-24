package com.m3.util.enumlike

import play.api.data.format.Formatter
import play.api.mvc.{ JavascriptLitteral, PathBindable, QueryStringBindable }

import scala.reflect.ClassTag

/**
 * Use this to add form and routes bindings to your enum
 */
trait PlayFormat[E <: EnumLike] {
  self: EnumCompanion[E] =>

  implicit def enumLikeFormatter(implicit base: Formatter[E#ValueType], tag: ClassTag[E]): Formatter[E] = PlayFormatter.enumLikeFormatter

  implicit def enumLikePathBindable(implicit base: PathBindable[E#ValueType]): PathBindable[E] = PlayFormatter.enumLikePathBindable

  implicit def enumLikeQueryStringBindable(implicit base: QueryStringBindable[E#ValueType]): QueryStringBindable[E] = PlayFormatter.enumLikeQueryStringBindable

  implicit def enumLikeJavascriptLitteral(implicit base: JavascriptLitteral[E#ValueType]): JavascriptLitteral[E] = PlayFormatter.enumLikeJavascriptLitteral
}
