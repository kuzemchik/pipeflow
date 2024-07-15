package org.significatum.pipeline.spark

import org.apache.spark.sql.SparkSession

class TestEnv extends Env {
  lazy val spark: SparkSession =
    SparkSession.builder()
      .appName("test")
      .master("local")
      .getOrCreate()
}
