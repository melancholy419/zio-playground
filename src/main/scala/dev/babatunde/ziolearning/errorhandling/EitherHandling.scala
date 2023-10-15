package dev.babatunde.ziolearning.errorhandling

import zio._

object EitherHandling extends App {
  val zEither = ZIO.fail("Cannot process").either
  val zMatch = zEither.map {
    case Right(value)    => value
    case Left(exception) => println(s"Exception occurred with message: $exception")
  }
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    (for {
      value <- zMatch
      _ <- console.putStrLn(s"$value")
    } yield ()).exitCode
  }
}
