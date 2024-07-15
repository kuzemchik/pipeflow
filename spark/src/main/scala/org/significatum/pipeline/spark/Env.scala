package org.significatum.pipeline.spark

import org.apache.spark.sql.SparkSession

trait Env {
  def spark:SparkSession
}
