package com.m3.util.enumlike

abstract class SimpleEnumLike[E](val value: E) extends EnumLike {
  type ValueType = E
  override def toString = value.toString
}
