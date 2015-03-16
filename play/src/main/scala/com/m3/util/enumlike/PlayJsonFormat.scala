package com.m3.util.enumlike

import play.api.libs.json._

trait PlayJsonFormat { self: EssentialEnumCompanion =>

  implicit def jsonFormat(implicit f: Format[EnumLikeType#ValueType]): Format[EnumLikeType] = new Format[EnumLikeType] {
    override def writes(o: EnumLikeType): JsValue = f.writes(o.value)
    override def reads(json: JsValue): JsResult[EnumLikeType] = f.reads(json).flatMap { value =>
      valueOf(value).fold[JsResult[EnumLikeType]](JsError(s"Could not convert $value"))(JsSuccess(_, JsPath()))
    }
  }

}
