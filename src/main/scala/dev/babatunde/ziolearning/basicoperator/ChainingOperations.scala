package dev.babatunde.ziolearning.basicoperator

import zio._
import zio.console.Console

import java.io.IOException
/*
 * This object shows how to chain multiple effects using the for comprehension
 * */
object ChainingOperations extends App {

  private val program: ZIO[Console, IOException, Unit] = for {
    _ <- console.putStrLn("Enter your name")
    name <- console.getStrLn
    _ <- console.putStrLn(s"Welcome $name")
  } yield ()
  override def run(args: List[String]) = program.exitCode
}
