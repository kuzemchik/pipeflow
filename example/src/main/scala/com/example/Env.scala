package com.example

import com.typesafe.config.Config
import org.apache.spark.sql.SparkSession

trait Env {
  val spark: SparkSession
  val config: Config
}
