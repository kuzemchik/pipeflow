package org.significatum.pipeline.spark.dao

import org.apache.spark.sql.DataFrame
import org.significatum.pipeline.spark.TestEnv

class DataPointsDao(env:TestEnv) {
  def loadPoints():DataFrame = { //parametrized
    val filename = this.getClass.getResource("/test.csv").getFile
    env.spark.read
      .option("header", "true")
      .option("delimiter", ",")
      .csv(filename)
  }
}
