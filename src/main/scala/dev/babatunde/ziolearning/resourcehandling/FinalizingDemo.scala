package dev.babatunde.ziolearning.resourcehandling

import zio._
object FinalizingDemo extends App {

  val finalizer: UIO[Unit] = IO.effectTotal(println("Finalizing here"))

  val finalized = ZIO
    .fail("I am failing here")
    .ensuring(finalizer)

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = finalized.exitCode
}
