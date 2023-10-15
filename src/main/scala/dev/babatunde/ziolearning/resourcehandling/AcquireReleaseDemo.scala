package dev.babatunde.ziolearning.resourcehandling

import zio._

import java.io.{BufferedReader, FileReader}
object AcquireReleaseDemo extends App {

  def lines(filename: String): Task[String] = {
    def readlines(reader: BufferedReader): Task[String] = Task.effect(reader.readLine())
    def releaseReader(reader: BufferedReader): UIO[Unit] = Task.effectTotal(reader.close())
    def acquireReader(file: String) = Task.effect(new BufferedReader(new FileReader(file)))

    Task.bracket(acquireReader(filename), releaseReader, readlines)
  }
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    (for {
      line <- lines("main.txt")
      _ <- console.putStrLn(line)
    } yield ()).exitCode
}
