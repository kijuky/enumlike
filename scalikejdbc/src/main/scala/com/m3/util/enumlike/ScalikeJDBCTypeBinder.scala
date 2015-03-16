package com.m3.util.enumlike

import scalikejdbc._

trait ScalikeJDBCTypeBinder { self: EssentialEnumCompanion =>

  implicit def optionalTypeBinder(implicit ev: TypeBinder[EnumLikeType#ValueType]): TypeBinder[Option[EnumLikeType]] = ev.map(valueOf)
  implicit def typeBinder(implicit ev: TypeBinder[EnumLikeType#ValueType]): TypeBinder[EnumLikeType] = optionalTypeBinder(ev).map(_.get)

}
