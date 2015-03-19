package com.m3.util.enumlike

import enumeratum.EnumMacros

import scala.language.experimental.macros

trait EnumCompanion[A <: EnumLike] extends EssentialEnumCompanion {

  type EnumLikeType = A

  def values: IndexedSeq[A]

  /**
   * Note: no guarantees are made about ordering, so this method returns a `Set`
   */
  protected def findValues: Set[A] = macro EnumMacros.findValuesImpl[A]

  def valueOf(value: A#ValueType): Option[A] = values.find(_.value == value)

  implicit def companion: EnumCompanion[A] = this

  implicit def order: Ordering[A] = Ordering.by(values.indexOf)

}
