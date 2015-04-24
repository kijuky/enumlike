package com.m3.util.enumlike

import play.api.libs.json._

trait PlayJsonFormat[E <: EnumLike] {
  self: EnumCompanion[E] =>

  implicit def enumlikeJsonFormat(implicit f: Format[E#ValueType]): Format[E] = PlayJsonFormatter.enumlikeJsonFormat

}
