package com.example.flow

import com.example.{Env, Repository}
import com.example.step.AggregatePipe
import org.significatum.pipeline.core.Pipe

object DataPointFlow {

  def executeFlow()(implicit env: Env, repo: Repository): Unit = {
    for {
      csv <- Pipe.out(repo.dataPoints.loadPoints())
      aggregated <- AggregatePipe(csv)
    } {
      repo.dataPoints.writeAggregates(aggregated)
      aggregated.show()
    }
  }

}
