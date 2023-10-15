package dev.babatunde.ziolearning.basicoperator

import zio.console.Console
import zio.{ExitCode, URIO, ZIO}

import scala.io.StdIn

object SequentialOperations extends zio.App {

  val firstNameEffect = ZIO.effect(StdIn.readLine("Enter first name \n"))
  val secondNameEffect = ZIO.effect(StdIn.readLine("Enter Second Name\n"))

  override def run(args: List[String]): URIO[Any with Console, ExitCode] = {
    val sequencedOperation = firstNameEffect
      .zipWith(secondNameEffect)((firstname, lastname) => s"Full name is $firstname $lastname")
      .map(println)
    sequencedOperation.exitCode
  }
}
