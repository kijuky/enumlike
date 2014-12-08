package com.m3.util.enumlike

import com.m3.util.enumlike.Quark.Charm
import org.scalatest.{ Matchers, FlatSpec }

class MacroMagicSpec extends FlatSpec with Matchers {

  behavior of "#values"

  it should "return all the quark flavours in some unspecified order" in {
    Quark.values should contain allOf (Quark.Up, Quark.Down, Quark.Strange, Quark.Charm, Quark.Bottom, Quark.Top)
  }

  behavior of "#valueOf"

  it should "deserialize from a string" in {
    Quark.valueOf("Charm") should be(Some(Charm))
  }

}
