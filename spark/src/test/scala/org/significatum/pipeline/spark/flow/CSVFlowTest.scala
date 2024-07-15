package org.significatum.pipeline.spark.flow

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should


class CSVFlowTest extends AnyFlatSpec with should.Matchers  {


  "CSV Flow" should "generate number" in {
    CSVFlow.executeFlow()


  }
}
