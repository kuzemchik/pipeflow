lazy val scala213 = "2.13.13"
lazy val scala212 = "2.12.19"
lazy val supportedScalaVersions = List(scala212, scala213)

lazy val sparkVersion = "3.5.1"

ThisBuild / organization := "com.significatum"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := scala213
javacOptions ++= Seq("-source", "11")

lazy val root = (project in file("."))
  .settings(
    name := "pipeline",
    crossScalaVersions := Nil,
  ).aggregate(core, util, spark)

lazy val core = (project in file("core"))
  .settings(
    name := "pipeline-core",
    crossScalaVersions := supportedScalaVersions,
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.18" % "test",
      "org.scalatest" %% "scalatest" % "3.2.18" % "test",
    )
  )

lazy val spark = (project in file("spark"))
  .settings(
    name := "pipeline-spark",
    crossScalaVersions := supportedScalaVersions,
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.18"% "test",
      "org.scalatest" %% "scalatest" % "3.2.18" % "test",
      "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
      "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"
    )
  ).dependsOn(core)

lazy val util = (project in file("util"))
  .settings(
    name := "pipeline-util",
    crossScalaVersions := supportedScalaVersions,
  ).dependsOn(core)
