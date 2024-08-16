package com.example.step

import com.example.SharedTestEnv
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should


class AggregatePipeSpec extends AnyFlatSpec with should.Matchers with SharedTestEnv {


  "AggregatePipe" should "Aggregate pings" in {

    import env.spark.implicits._

    val input = Seq(
      (1721010925,1,200),
      (1720924522,1,80)
    ).toDF("timestamp","user_id","ping")

    val output = Seq(
      (1,140),
    ).toDF("user_id","avg_ping")

    AggregatePipe(input).result.except(output).isEmpty shouldBe true
  }
}
