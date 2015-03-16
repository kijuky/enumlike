package com.m3.util.enumlike

import com.m3.util.enumlike.Beatle._
import org.scalatest.{ Matchers, FlatSpec }

class EnumCompanionSpec extends FlatSpec with Matchers {

  behavior of "#valueOf"

  it should "deserialize from a string" in {
    Beatle.valueOf("George") should be(Some(George))
  }

  it should "return None if passed an unknown string" in {
    Beatle.valueOf("Georgina") should be(None)
  }

  behavior of "ordering"

  it should "use the ordering defined in the #values list" in {
    import Helper._
    George isBetterThan Ringo should be(true) // universally agreed
    John isBetterThan Paul should be(true) // more controversial, but equally true
  }

}

object Helper {

  implicit class OrderedBeatle(val b: Beatle) extends AnyVal {
    def isBetterThan(b2: Beatle)(implicit ordering: Ordering[Beatle]): Boolean = ordering.lt(b, b2)
  }

}

