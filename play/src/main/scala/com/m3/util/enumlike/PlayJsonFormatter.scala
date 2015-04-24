package com.m3.util.enumlike

import play.api.libs.json._

/**
 * Use this when you do not want your enums to depend on play-json, but still have the formatters
 */
trait PlayJsonFormatter {

  implicit def jsonWrites[E <: EnumLike](implicit f: Format[E#ValueType]): Writes[E] = Writes[E] {
    e => f.writes(e.value)
  }

  implicit def jsonReads[E <: EnumLike](implicit f: Format[E#ValueType], c: EnumCompanion[E]): Reads[E] = Reads[E] {
    f.reads(_).flatMap { value =>
      c.valueOf(value).fold[JsResult[E]](JsError(s"Could not convert $value"))(JsSuccess(_, JsPath()))
    }
  }

  implicit def jsonFormat[E <: EnumLike](implicit f: Format[E#ValueType], c: EnumCompanion[E]): Format[E] = Format[E](jsonReads, jsonWrites)
}

object PlayJsonFormatter extends PlayJsonFormatter

