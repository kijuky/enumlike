package com.m3.util.enumlike

import scalikejdbc._

trait ScalikeJDBCTypeBinder[E <: EnumLike] {
  self: EnumCompanion[E] =>

  implicit def typeBinder(implicit ev: TypeBinder[EnumLikeType#ValueType]): TypeBinder[EnumLikeType] = ScalikeJDBCTypeBinding.typeBinder

  implicit def optionalTypeBinder(implicit ev: TypeBinder[EnumLikeType#ValueType]): TypeBinder[Option[EnumLikeType]] = ScalikeJDBCTypeBinding.optionalTypeBinder
}
