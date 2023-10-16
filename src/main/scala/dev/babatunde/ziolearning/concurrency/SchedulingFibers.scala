package dev.babatunde.ziolearning.concurrency

import zio._

object SchedulingFibers extends App {

  val taskOne = ZIO.succeed("First task")
  val taskTwo = ZIO.succeed("Second task")
  val taskThree = ZIO.succeed("Third task")

  def printThreadName = s"${Thread.currentThread().getName}"
  def concurrentTasks = for {
    firstFiber <- taskOne.debug(printThreadName).fork
    secondFiber <- taskTwo.debug(printThreadName).fork
    combinedFiber = firstFiber.zip(secondFiber)
    result <- combinedFiber.join.debug(printThreadName)
    _ <- ZIO.succeed(s"$result is done!").debug(printThreadName) *> taskThree.debug(printThreadName)
  } yield ()
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = concurrentTasks.exitCode
}
