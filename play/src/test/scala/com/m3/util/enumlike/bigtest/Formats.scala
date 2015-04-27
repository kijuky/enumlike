package com.m3.util.enumlike.bigtest

import com.m3.util.enumlike.PlayJsonFormatter
import play.api.libs.json._

trait Formats {
  import PlayJsonFormatter._
  implicit val carMakerFormat = {
    implicitly[Format[CarMaker]]
  }
  implicit val driverFormat = Json.format[Driver]
  implicit val carFormat = Json.format[Car]
}
