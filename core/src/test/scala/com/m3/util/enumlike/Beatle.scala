package com.m3.util.enumlike

sealed trait Beatle extends StringEnumLike

object Beatle extends EnumCompanion[Beatle] {

  // ordered by descending talent
  case object John extends Beatle
  case object George extends Beatle
  case object Paul extends Beatle
  case object Ringo extends Beatle

  val values = findValues

}
