package org.significatum.pipeline.core.pipes

import org.significatum.pipeline.core.Pipe

case class CounterPipe(var value:Long = 0) extends Pipe[Long] {
  def get(): Long = {
    value += 1
    value
  }
}
