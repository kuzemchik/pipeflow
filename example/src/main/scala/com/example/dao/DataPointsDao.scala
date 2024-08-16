package com.example.dao

import com.example.Env
import org.apache.spark.sql.DataFrame

class DataPointsDao(env:Env) {
  def loadPoints():DataFrame = { //parametrized
    val filename = this.getClass.getResource("/pings.csv").getFile
    env.spark.read
      .option("header", "true")
      .option("delimiter", ",")
      .csv(filename)
  }

  def writeAggregates(df: DataFrame): Unit = {
    val location = env.config.getString("com.example.data.points.aggregate.location")
    df
      .repartition(10)
      .write
      .mode("overwrite")
//      .parquet(location)
  }
}
