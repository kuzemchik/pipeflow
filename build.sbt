lazy val scala = "2.13.14"

lazy val sparkVersion = "3.5.2"
lazy val scalaTestVersion = "3.2.19"


ThisBuild / organization := "com.significatum"
ThisBuild / version      := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := scala

// fork tests in order to pick up javaOptions when running tests
lazy val opens:Seq[String] = Seq(
  "--add-opens=java.base/java.lang=ALL-UNNAMED",
  "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",
  "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
  "--add-opens=java.base/java.io=ALL-UNNAMED",
  "--add-opens=java.base/java.net=ALL-UNNAMED",
  "--add-opens=java.base/java.nio=ALL-UNNAMED",
  "--add-opens=java.base/java.util=ALL-UNNAMED",
  "--add-opens=java.base/java.util.concurrent=ALL-UNNAMED",
  "--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED",
  "--add-opens=java.base/jdk.internal.ref=ALL-UNNAMED",
  "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
  "--add-opens=java.base/sun.nio.cs=ALL-UNNAMED",
  "--add-opens=java.base/sun.security.action=ALL-UNNAMED",
  "--add-opens=java.base/sun.util.calendar=ALL-UNNAMED",
  "--add-opens=java.security.jgss/sun.security.krb5=ALL-UNNAMED",
  "-Djdk.reflect.useDirectMethodHandle=false",
  "-XX:+IgnoreUnrecognizedVMOptions",
  )





lazy val global = Seq(
  javacOptions ++= Seq("-source", "11"),
  scalacOptions ++= Seq("-deprecation","-feature")
)


lazy val root = (project in file(".")).settings(global)
  .settings(
    name := "pipeline",
    crossScalaVersions := Nil,
  ).aggregate(core, example)

lazy val core = (project in file("core")).settings(global)
  .settings(
    name := "pipeline-core",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % scalaTestVersion % Test,
      "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    )
  )

lazy val example = (project in file("example")).settings(global)
  .settings(
    name := "pipeline-spark-example",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % sparkVersion % Provided,
      "org.apache.spark" %% "spark-sql" % sparkVersion % Provided,
      "com.typesafe" % "config" % "1.4.3",
      "org.scalactic" %% "scalactic" % scalaTestVersion % Test,
      "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
    ),
//    Test / envVars += "JDK_JAVA_OPTIONS" -> opens.mkString(" "),
    Test / fork := true,
    Test / javaOptions ++= opens,
//    Test / javaOptions += ,
  ).dependsOn(core)

lazy val example_jar = (project in file("example_jar")).settings(global)
  .settings(
    name := "pipeline-spark-example",
    assembly / assemblyJarName := s"spark-example-${version.value}.jar",
  ).dependsOn(example)
