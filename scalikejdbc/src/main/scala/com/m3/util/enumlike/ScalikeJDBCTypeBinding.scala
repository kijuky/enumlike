package com.m3.util.enumlike

import scalikejdbc._

trait ScalikeJDBCTypeBinding {

  implicit def typeBinder[E <: EnumLike](implicit ev: TypeBinder[E#ValueType], c: EnumCompanion[E]): TypeBinder[E] = optionalTypeBinder.map(_.get)
  implicit def optionalTypeBinder[E <: EnumLike](implicit ev: TypeBinder[E#ValueType], c: EnumCompanion[E]): TypeBinder[Option[E]] = ev.map(c.valueOf)
}

object ScalikeJDBCTypeBinding extends ScalikeJDBCTypeBinding
