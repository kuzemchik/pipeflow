package org.significatum.pipeline.example.test

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.significatum.pipeline.example.flow.ExampleFlow


class ExampleFlowTest extends AnyFlatSpec with should.Matchers  {


  "Example Flow" should "Aggregate pings" in {
    ExampleFlow.executeFlow()
  }
}
