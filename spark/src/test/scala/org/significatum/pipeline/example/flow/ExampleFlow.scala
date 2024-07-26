package org.significatum.pipeline.example.flow

import org.significatum.pipeline.core.Pipe
import org.significatum.pipeline.example.step.AggregatePipe
import org.significatum.pipeline.example.{TestEnv, TestRepository}

object ExampleFlow {

  def executeFlow(): Unit = {
    val env = new TestEnv()
    val repo = new TestRepository(env)

    for {
      csv <- Pipe.out(repo.dataPoints.loadPoints())
      aggregated <- AggregatePipe(csv)
    } {
      aggregated.show()
    }
  }

}
