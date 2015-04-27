package com.m3.util.enumlike

import play.api.libs.json._

/**
 * Use this when you do not want your enums to depend on play-json, but still have the formatters
 */
trait PlayJsonFormatter {

  private[enumlike] def enumlikeJsonReads[E <: EnumLike](implicit r: Reads[E#ValueType], c: EssentialEnumCompanion { type EnumLikeType = E }): Reads[E] = Reads[E] {
    r.reads(_).flatMap { value =>
      c.valueOf(value).fold[JsResult[E]](JsError(s"Could not convert $value"))(JsSuccess(_, JsPath()))
    }
  }

  private[enumlike] def enumlikeJsonWrites[E <: EnumLike](implicit w: Writes[E#ValueType]): Writes[E] = Writes[E] {
    e => w.writes(e.value)
  }

  implicit def enumlikeJsonFormat[E <: EnumLike](implicit r: Reads[E#ValueType], w: Writes[E#ValueType], c: EnumCompanion[E]): Format[E] = {
    Format[E](
      enumlikeJsonReads(r, c),
      enumlikeJsonWrites(w)
    )
  }
}

object PlayJsonFormatter extends PlayJsonFormatter

