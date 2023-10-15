package dev.babatunde.ziolearning.errorhandling

import zio._

import scala.io.Source
object FallBackDemo extends App {

  val zfile = IO(Source.fromFile("main1.txt").getLines().mkString("\n"))
    .orElse(IO("Could not get the right file. Falling back on this"))
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    (for {
      value <- zfile
      _ <- console.putStrLn(s"$value")
    } yield ()).exitCode
}
