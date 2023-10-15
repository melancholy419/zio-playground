package dev.babatunde.ziolearning.errorhandling

import zio._

object CatchAllDemo extends App {
  val zFailed = ZIO.fail("Exception occurred")
  val zCatchAll = zFailed.catchAll { _ => ZIO.succeed("Error successfully caught here") }
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = (for {
    value <- zCatchAll
    _ <- console.putStrLn(value)
  } yield ()).exitCode
}
