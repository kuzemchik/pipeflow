package com.example

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.SparkSession

object ClusterEnv extends Env {
  lazy val spark: SparkSession = {

    val session = SparkSession.builder()
      .appName("Example App")
      .getOrCreate()
    session.sparkContext.setLogLevel("ERROR")
    session
  }

  lazy val config: Config = ConfigFactory.load()
}
