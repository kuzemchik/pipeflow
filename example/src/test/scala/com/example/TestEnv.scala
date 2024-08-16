package com.example

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.launcher.JavaModuleOptions
import org.apache.spark.sql.SparkSession

object TestEnv extends Env {
  lazy val spark: SparkSession = {
    val session = SparkSession.builder()
      .appName("Example App Test")
      .master("local[8]")
      .getOrCreate()
    session.sparkContext.setLogLevel("ERROR")
    session
  }

  lazy val config: Config = ConfigFactory.load()
}
