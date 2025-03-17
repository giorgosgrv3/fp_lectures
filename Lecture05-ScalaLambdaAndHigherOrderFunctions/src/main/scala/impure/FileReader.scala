package impure
import io.circe._
import io.circe.parser._
import scala.util.{Try, Success, Failure}
object FileReader {
  // Read JSON tweets from a file
  def readJsonFromFile(filename: String): Option[List[String]] = {
    Try {
      val source = scala.io.Source.fromFile(filename)
      val lines = source.getLines().toList
      source.close()
      lines
    } match {
      case Success(lines) => Some(lines)
      case Failure(_) => None
    }
  }

}
