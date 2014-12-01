package com.m3.util.enumlike

import play.api.mvc.{ QueryStringBindable, PathBindable }

trait CustomBinderBuilders {

  /**
   * Given an enumeration, returns a [[PathBindable]], which is useful for binding and
   * unbinding path params from routes
   */
  def enumPathBindable[E <: Enumeration](e: E): PathBindable[E#Value] = new PathBindable.Parsing[E#Value](
    e.withName(_),
    _.toString,
    (key, exception) => s"$key should have one of the following values: ${e.values.mkString("[", ", ", "]")}"
  )

  /**
   * Given an enum like sealed objects, returns a [[PathBindable]], which is useful for binding and
   * unbinding path params from routes
   */
  def enumLikePathBindable[A <: EnumLike](implicit ev: EnumCompanion[A], base: PathBindable[A#ValueType]): PathBindable[A] = new PathBindable[A] {
    override def bind(key: String, value: String) = for {
      v <- base.bind(key, value).right
      a <- ev.valueOf(v).toRight(s"$key should have one of the following values: ${ev.values.mkString("[", ", ", "]")}").right
    } yield a
    override def unbind(key: String, value: A) = base.unbind(key, value.value)
  }

  /**
   * Given an enumeration, returns a [[QueryStringBindable]], which is useful for binding and
   * unbinding query string params from routes
   */
  def enumQueryStringBindable[E <: Enumeration](e: E): QueryStringBindable[E#Value] = new QueryStringBindable.Parsing[E#Value](
    e.withName(_),
    _.toString,
    (key, exception) => s"$key should have one of the following values: ${e.values.mkString("[", ", ", "]")}"
  )

  /**
   * Given an enum like sealed objects, returns a [[QueryStringBindable]], which is useful for binding and
   * unbinding query string params from routes
   */
  def enumLikeQueryStringBindable[A <: EnumLike](implicit ev: EnumCompanion[A], base: QueryStringBindable[A#ValueType]): QueryStringBindable[A] = {
    new QueryStringBindable[A] {
      override def bind(key: String, params: Map[String, Seq[String]]) = base.bind(key, params).map {
        _.right.flatMap { value =>
          ev.valueOf(value).toRight(s"$key should have one of the following values: ${ev.values.mkString("[", ", ", "]")}")
        }
      }
      override def unbind(key: String, value: A) = base.unbind(key, value.value)
    }
  }

}
