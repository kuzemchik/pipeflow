package org.significatum.pipeline.example.dao

import org.apache.spark.sql.DataFrame
import org.significatum.pipeline.example.TestEnv

class DataPointsDao(env:TestEnv) {
  def loadPoints():DataFrame = { //parametrized
    val filename = this.getClass.getResource("/pings.csv").getFile
    env.spark.read
      .option("header", "true")
      .option("delimiter", ",")
      .csv(filename)
  }
}
