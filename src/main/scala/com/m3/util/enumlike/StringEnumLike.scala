package com.m3.util.enumlike

trait StringEnumLike extends EnumLike {
  type ValueType = String
  lazy val value = toString
}
