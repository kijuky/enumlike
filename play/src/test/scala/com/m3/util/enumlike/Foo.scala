package com.m3.util.enumlike

sealed trait Foo extends StringEnumLike
object Foo extends EnumCompanion[Foo] with PlayJsonFormat {

  case object Aaa extends Foo
  case object Bbb extends Foo
  case object Ccc extends Foo

  val values = IndexedSeq(Aaa, Bbb, Ccc)

}
