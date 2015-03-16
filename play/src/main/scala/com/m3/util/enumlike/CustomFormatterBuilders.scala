package com.m3.util.enumlike

import play.api.data.FormError
import play.api.data.format.Formatter

import scala.reflect.ClassTag

trait CustomFormatterBuilders {

  implicit def enumLikeFormatter[E <: EnumLike](implicit ev: EnumCompanion[E], base: Formatter[E#ValueType], tag: ClassTag[E]) = new Formatter[E] {
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
}
