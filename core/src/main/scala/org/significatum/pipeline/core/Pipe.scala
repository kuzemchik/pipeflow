package org.significatum.pipeline.core


object Pipe {
  def out[T](value: => T):Pipe[T] = {
    new Pipe[T] {
      def get(): T = value
    }
  }
}

trait Pipe[T] extends IterableOnce[T]  { self =>

  protected def get():T

  private var cache:Option[T] = None
  def result:T =  {
    if (cache.isEmpty) {
      this.synchronized {
        if (cache.isEmpty) {
          cache = Some(get())
        }
      }
    }
    cache.get
  }

  def flatMap[O](f: Pipe[T] => O): O = {
    f(self)
  }

  def map[O](f: Pipe[T] => O): Pipe[O] = {
    new Pipe[O] {
      def get(): O = {
        f(self)
      }
    }
  }

  def foreach[O](f: Pipe[T] => O): Unit = {
    f(self)
  }

  override def iterator: Iterator[T] = {
    Seq(result).iterator
  }
}


