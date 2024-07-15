package org.significatum.pipeline.spark.flow

import org.significatum.pipeline.core.Pipe
import org.significatum.pipeline.spark.{TestEnv, TestRepository}

object CSVFlow {
  def executeFlow() = {
    val env = new TestEnv()
    val repo = new TestRepository(env)

    for {
      csv <- Pipe.wrap(repo.dataPoints.loadPoints())
    } {
      csv.show()
    }

  }
}
