package dev.babatunde.ziolearning.errorhandling

import zio._

import scala.io.Source
object FoldingDemo extends App {

  val fileReader = IO(Source.fromFile("main1.txt").getLines.mkString("\n"))
    .foldM(
      _ => IO(Source.fromFile("backup1.txt").getLines.mkString("\n")),
      data => ZIO.succeed(data)
    )
    .orElse(IO("Interesting stuff here"))
  override def run(args: List[String]) =
    (for {
      source <- fileReader
      value <- IO(source)
      _ <- console.putStrLn(value)
    } yield ()).exitCode
}
