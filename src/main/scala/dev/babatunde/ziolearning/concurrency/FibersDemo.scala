package dev.babatunde.ziolearning.concurrency

import zio._
object FibersDemo extends App {

  val wakeUp = ZIO.succeed("Wake up")
  val createPr = ZIO.succeed("Creating new pull request")
  val doSomething = ZIO.succeed("Doing something new")

  def printThreadName = s"${Thread.currentThread().getName}"
  def syncAll = for {
    _ <- wakeUp.debug(printThreadName)
    _ <- createPr.debug(printThreadName).fork
    _ <- doSomething.debug(printThreadName).fork
  } yield ()
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = syncAll.exitCode
}
