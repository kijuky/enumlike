package com.m3.util.enumlike.bigtest

import com.m3.util.enumlike.{ EnumCompanion, StringEnumLike }

sealed trait CarMaker extends StringEnumLike

object CarMaker extends EnumCompanion[CarMaker] {
  object Volvo extends CarMaker
  object Toyota extends CarMaker
  object Honda extends CarMaker

  val values: IndexedSeq[CarMaker] = findValues
}
