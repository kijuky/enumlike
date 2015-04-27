package com.m3.util.enumlike

import play.api.libs.json._

trait PlayJsonFormat {
  self: EssentialEnumCompanion =>

  implicit def enumlikeJsonFormat(implicit f: Format[EnumLikeType#ValueType]): Format[EnumLikeType] = {
    Format[EnumLikeType](
      PlayJsonFormatter.enumlikeJsonReads(f, self),
      PlayJsonFormatter.enumlikeJsonWrites(f)
    )
  }

}
