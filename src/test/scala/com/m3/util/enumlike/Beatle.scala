package com.m3.util.enumlike

sealed trait Beatle extends StringEnumLike

object Beatle extends EnumCompanion[Beatle] {

  case object John extends Beatle
  case object Paul extends Beatle
  case object George extends Beatle
  case object Ringo extends Beatle

  // ordered by descending talent
  def values = IndexedSeq(John, George, Paul, Ringo)

}
