package org.significatum.pipeline.core.pipes

import org.significatum.pipeline.core.Pipe

case class ThrowPipe(value:String) extends Pipe[String] {
  def get(): String = {
    throw new RuntimeException(value)
  }
}
