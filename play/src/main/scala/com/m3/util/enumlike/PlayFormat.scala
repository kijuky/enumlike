package com.m3.util.enumlike

import play.api.data.format.Formatter
import play.api.mvc.{ JavascriptLitteral, PathBindable, QueryStringBindable }

import scala.reflect.ClassTag

/**
 * Use this to add form and routes bindings to your enum
 */
trait PlayFormat {
  self: EssentialEnumCompanion =>

  implicit def enumLikeFormatter(implicit base: Formatter[EnumLikeType#ValueType], tag: ClassTag[EnumLikeType]): Formatter[EnumLikeType] = PlayFormatter.enumLikeFormatter[EnumLikeType](self, base, tag)

  implicit def enumLikePathBindable(implicit base: PathBindable[EnumLikeType#ValueType]): PathBindable[EnumLikeType] = PlayFormatter.enumLikePathBindable[EnumLikeType](self, base)

  implicit def enumLikeQueryStringBindable(implicit base: QueryStringBindable[EnumLikeType#ValueType]): QueryStringBindable[EnumLikeType] = PlayFormatter.enumLikeQueryStringBindable[EnumLikeType](self, base)

  implicit def enumLikeJavascriptLitteral(implicit base: JavascriptLitteral[EnumLikeType#ValueType]): JavascriptLitteral[EnumLikeType] = PlayFormatter.enumLikeJavascriptLitteral[EnumLikeType](self, base)
}
