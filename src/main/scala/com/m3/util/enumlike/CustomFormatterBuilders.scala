package com.m3.util.enumlike

import play.api.data.FormError
import play.api.data.format.Formatter

import scala.reflect.ClassTag

trait CustomFormatterBuilders {

  def enumFormatter[E <: Enumeration](e: E) = new Formatter[E#Value] {
    def bind(key: String, data: Map[String, String]): Either[Seq[FormError], E#Value] =
      data.get(key).flatMap(s => e.values.find(_.toString == s)) match {
        case Some(v) => Right(v)
        case None => Left(Seq(FormError(key, "error.invalid.enum", Seq(e.getClass.getName))))
      }

    def unbind(key: String, value: E#Value): Map[String, String] = Map(key -> value.toString)
  }

  def enumLikeFormatter[E <: EnumLike](implicit ev: EnumCompanion[E], base: Formatter[E#ValueType], tag: ClassTag[E]) = new Formatter[E] {
    def bind(key: String, data: Map[String, String]): Either[Seq[FormError], E] = {
      base.bind(key, data) match {
        case Right(v) => ev.values.find(_.value == v) match {
          case None => Left(Seq(FormError(key, "error.invalid.enum", Seq(tag.runtimeClass.getName))))
          case Some(e) => Right(e)
        }
        case Left(errors) => Left(errors)
      }
    }

    def unbind(key: String, value: E): Map[String, String] = base.unbind(key, value.value)
  }
}
