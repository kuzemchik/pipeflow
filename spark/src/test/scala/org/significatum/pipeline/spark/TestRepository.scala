package org.significatum.pipeline.spark

import org.significatum.pipeline.spark.dao.DataPointsDao

class TestRepository(val env:TestEnv) extends Repository {
  lazy val dataPoints = new DataPointsDao(env)
}
