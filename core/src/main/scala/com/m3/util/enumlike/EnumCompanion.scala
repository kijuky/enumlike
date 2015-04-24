package com.m3.util.enumlike

import enumeratum.EnumMacros

import scala.language.experimental.macros

trait EnumCompanion[A <: EnumLike] extends EssentialEnumCompanion {

  type EnumLikeType = A

  /**
   * You must override this with when implementing this trait, when the [[A]] type is concretely defined.
   *
   * E.g. <code>(lazy) val values = findValues</code>
   */
  val values: IndexedSeq[A]

  /**
   * Actual order is the declaration order.
   */
  protected final def findValues: IndexedSeq[A] = macro EnumMacros.findValuesImpl[A]

  def valueOf(value: A#ValueType): Option[A] = values.find(_.value == value)

  implicit def companion: EnumCompanion[A] = this

  implicit def order: Ordering[A] = Ordering.by(values.indexOf)

}
