package org.significatum.pipeline

import scala.language.implicitConversions

package object core {
  implicit def extract[T](extractable: Pipe[T]): T =
    extractable.result
}
