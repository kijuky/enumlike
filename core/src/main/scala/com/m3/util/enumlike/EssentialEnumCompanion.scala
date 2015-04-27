package com.m3.util.enumlike

trait EssentialEnumCompanion {

  type EnumLikeType <: EnumLike

  def valueOf(value: EnumLikeType#ValueType): Option[EnumLikeType]

  def values: IndexedSeq[EnumLikeType]

}
