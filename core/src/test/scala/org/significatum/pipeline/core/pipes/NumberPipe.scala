package org.significatum.pipeline.core.pipes

import org.significatum.pipeline.core.Pipe

case class NumberPipe(value:Long) extends Pipe[Long] {
  def get(): Long = {
    value
  }
}
