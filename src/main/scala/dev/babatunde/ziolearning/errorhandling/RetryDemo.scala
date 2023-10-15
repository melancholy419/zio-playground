package dev.babatunde.ziolearning.errorhandling

import zio._

import scala.io.{BufferedSource, Source}
object RetryDemo extends App {

  val fileReader: ZIO[Any, Throwable, BufferedSource] = IO {
    println("Trying to read file")
    Source.fromFile("main1.txt")
  }.retryN(2).orElse(IO(Source.fromFile("backup.txt")))

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    (for {
      source <- fileReader
      value <- IO(source.getLines().mkString("\n"))
      _ <- console.putStrLn(s"$value")
    } yield ()).exitCode
}
