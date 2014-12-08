package com.m3.util.enumlike

sealed trait Quark extends StringEnumLike

object Quark extends EnumCompanion[Quark] {

  case object Up extends Quark
  case object Down extends Quark
  case object Strange extends Quark
  case object Charm extends Quark
  case object Bottom extends Quark
  case object Top extends Quark

  val values = findValues.toIndexedSeq

}
