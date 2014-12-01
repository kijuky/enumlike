import aether.Aether._
import com.typesafe.sbt.SbtGit._
import com.typesafe.sbt.SbtScalariform._
import sbt.Keys._
import sbt._
import sbtrelease.ReleasePlugin._

import scala.language.postfixOps

object EnumLikeBuild extends Build {
  val theScalaVersion = "2.11.4"
  val thePlayVersion = "2.3.6"

  val m3Resolver = "M3 internal Artifactory" at "http://maven:8081/artifactory/repo"

  lazy val enumLike = Project(id = "enumlike", base = file("."), settings = scalariformSettings ++ publishSettings ++ releaseSettings).settings(
    organization := "com.m3",
    name := "enumlike",
    scalaVersion := theScalaVersion,
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xlint"),
    updateOptions := updateOptions.value
      .withCircularDependencyLevel(CircularDependencyLevel.Error)
      .withCachedResolution(true),
    libraryDependencies := Seq(
      "com.typesafe.play" %% "play-ws" % thePlayVersion, // this library depends on play-json and Play MVC
      "com.typesafe.play" %% "play-test" % thePlayVersion % "test",
      "org.scalatestplus" %% "play" % "1.2.0" % "test",
      "org.scalatest" %% "scalatest" % "2.2.2" % "test"
    ).map(_.excludeAll(
        ExclusionRule(name = "slf4j-log4j12"),
        ExclusionRule(name = "slf4j-jdk14"),
        ExclusionRule(name = "slf4j-jcl"),
        ExclusionRule(name = "slf4j-nop"),
        ExclusionRule(name = "slf4j-simple")))
  )

  lazy val publishSettings = aetherPublishSettings :+
    (publishTo <<= version { (v: String) =>
      val base = "http://maven:8081/artifactory/"
      if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at base + "libs-snapshots")
      else Some("releases" at base + "libs-releases")
    })

}
