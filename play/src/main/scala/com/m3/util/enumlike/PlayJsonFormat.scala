package com.m3.util.enumlike

import play.api.libs.json._

trait PlayJsonFormat[E <: EnumLike] {
  self: EnumCompanion[E] =>

  implicit def jsonFormat(implicit f: Format[E#ValueType]): Format[E] = PlayJsonFormatter.jsonFormat

}
