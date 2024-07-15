package org.significatum.pipeline.core

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers._
import org.significatum.pipeline.core.pipes.{CounterPipe, NumberPipe, ThrowPipe}
import org.significatum.pipeline.core.extract

import scala.Console.in

class PipeTest extends AnyFlatSpec with should.Matchers {

  "Pipe" should "process foreach evaluations" in {
    for {
      n <- NumberPipe(10)
      v <- NumberPipe(5)
      s:Long <- NumberPipe( n + v )
    } {
      (s:Long) shouldBe 15
    }
  }

  it should "process flatmap evaluations" in {
    val res:Long = for {
      n <- NumberPipe(10)
      v <- NumberPipe(5)
      s <- NumberPipe(n + v)
    } yield s:Long
    (res) shouldBe 15


    val out = NumberPipe(10).flatMap( n =>
      NumberPipe(5).flatMap(v =>
        NumberPipe(n+v).map(v => v)
      )
    )

  }

  it should "process yield evaluations lazy" in {
    val res = for {
      c1 <- ThrowPipe("Executed first pipe")
      c2 <- ThrowPipe(s"$c1, Executed second pipe")
    } yield (c1,c2)
    res.result match { case (c1,c2) =>
      an [RuntimeException] shouldBe thrownBy(c1.result)
      an [RuntimeException] shouldBe thrownBy(c2.result)
    }
  }

  it should "process foreach evaluations lazy" in {
    for {
      n <- NumberPipe(1)
      c1 <- ThrowPipe(s"Executed first pipe with number $n")
      c2 <- ThrowPipe(s"$c1, Executed second pipe")
    } {
      an [RuntimeException] shouldBe thrownBy(c1.result)
      an [RuntimeException] shouldBe thrownBy(c2.result)
    }
  }


  it should "process foreach evaluations once" in {
    val res = for {
      n <- CounterPipe()
      v <- CounterPipe()
      s <- NumberPipe(n + v)
      s2 <- NumberPipe(n + v + s)
    } yield s+s2
    res.result shouldBe 6
  }

  it should "process foreach evaluations once p2" in {
    val res:(Long, Long, Long) = for {
      n <- CounterPipe()
      s <- NumberPipe(n + 1)
      s2 <- NumberPipe(n + 2)
      s3 <- NumberPipe(n + 3)
    } yield (s:Long, s2:Long, s3:Long)
    res shouldBe (2, 3, 4)
  }

  it should "allow iterations" in {

    var sum = 0L
    for {
      _ <- (1 to 10)
      n <- CounterPipe()
      v <- CounterPipe()
      s <- NumberPipe(n + v)
      s2 <- NumberPipe(n + v)
    } {
      sum = sum + s + s2
    }
    sum shouldBe 40
  }

  it should "allow yield iterations" in {
    val res: Seq[Long] = for {
      _ <- 1 to 5
      n <- CounterPipe()
      v <- CounterPipe()
      s <- NumberPipe(n + v)
      s2 <- NumberPipe(n + v)
    } yield (s + s2)

    res shouldBe Seq(4,4,4,4,4)
  }

}
