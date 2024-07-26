package org.significatum.pipeline.example

import org.apache.spark.sql.SparkSession
import org.significatum.pipeline.spark.Env

class TestEnv extends Env {
  lazy val spark: SparkSession = {

    val session = SparkSession.builder()
      .appName("test")
      .master("local")
      .getOrCreate()
    session.sparkContext.setLogLevel("ERROR")
    session
  }
}
