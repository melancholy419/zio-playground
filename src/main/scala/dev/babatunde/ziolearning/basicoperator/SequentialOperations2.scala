package dev.babatunde.ziolearning.basicoperator

import zio._

object SequentialOperations2 extends App {
  val stringNum = ZIO.succeed("43")
  val num = ZIO.succeed(42)
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
    num.zipWith(stringNum)((a, b) => println(s"$a $b")).exitCode
//    val result = for {
//    n <- num
//      str <- stringNum
//    } yield (str, num)
//    result.map(a => println(s"${a._1} ${a._2}")).exitCode
  }
}
