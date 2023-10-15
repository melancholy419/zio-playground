package dev.babatunde.ziolearning.errorhandling

import zio._
import zio.console.Console

case class InvalidUserException(message: String) extends RuntimeException {}
object CatchSomeDemo extends App {

  private def findUser(id: Long): String =
    if (id == 1)
      """
        |{
        | "firstName": "Babatunde",
        | "lastName": "Lawal",
        | "role": "Scala Engineer"
        | "salary": 1000000
        |}
        |""".stripMargin
    else throw InvalidUserException("User not found")

  private val zfailed: Task[String] = IO(findUser(0))
  private val zcatchSome: ZIO[Console, Throwable, Any] = zfailed.catchSome {
    case ex: InvalidUserException =>
      ZIO.succeed(ex.message)
    case _ => console.putStrLn("Invalid exception occurred")
  }
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = (for {
    value <- zcatchSome
    _ <- console.putStrLn(s"$value")
  } yield ()).exitCode
}
