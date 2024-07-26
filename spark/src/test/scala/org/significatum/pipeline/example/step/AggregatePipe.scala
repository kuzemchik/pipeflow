package org.significatum.pipeline.example.step

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.significatum.pipeline.core.Pipe

case class AggregatePipe(input:DataFrame) extends Pipe[DataFrame] {
  override protected def get(): DataFrame = {
    input.groupBy("user_id").agg(avg("ping") as "avg_ping")
  }
}
