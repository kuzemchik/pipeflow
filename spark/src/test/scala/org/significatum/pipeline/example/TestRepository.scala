package org.significatum.pipeline.example

import org.significatum.pipeline.example.dao.DataPointsDao
import org.significatum.pipeline.spark.Repository

class TestRepository(val env:TestEnv) extends Repository {
  lazy val dataPoints = new DataPointsDao(env)
}
