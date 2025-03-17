package pure
import io.circe._
import io.circe.parser._
case class Tweet(text: String, createdAt: String, user: String)

object TweetAnalytics {
  // Parse JSON tweets into a case class
  def parseTweets(jsonTweets: List[String]): List[Tweet] = {
    /*!!!In Scala, when you use flatMap on a collection of Option values,
      it will flatten the results while discarding any None values.!!!*/
    jsonTweets.flatMap { json =>
      val parsedJson = parse(json)
      parsedJson.toOption.flatMap { json =>
        val text = json.hcursor.downField("text").as[String].getOrElse("")
        val createdAt = json.hcursor.downField("created_at").as[String].getOrElse("")
        val user = json.hcursor.downField("user").as[String].getOrElse("")
        Some(Tweet(text, createdAt, user))
      }
    }
  }

  // Computes the number of tweets per user
  def tweetsPerUser(tweets: List[Tweet]): Map[String, Int] = {
    tweets.groupBy(_.user).view.mapValues(_.size).toMap
  }

  // Finds the user with the most tweets
  def topUser(tweets: List[Tweet]): Option[(String, Int)] = {
    tweetsPerUser(tweets).maxByOption(_._2)
  }

  // Computes the average length of tweets
  def averageTweetLength(tweets: List[Tweet]): Double = {
    val totalCharacters = tweets.map(_.text.length).sum.toDouble
    tweets.filterNot(_.text.isEmpty).map(_.text.length).sum.toDouble / tweets.size
  }
  //recall, i despise ifs
  /*def averageTweetLength(tweets: List[Tweet]): Double = {
    val totalCharacters = tweets.map(_.text.length).sum.toDouble
    if (tweets.nonEmpty) totalCharacters / tweets.size else 0
  }*/

  // Groups tweets by the day they were created
  def tweetsByDay(tweets: List[Tweet]): Map[String, Int] = {
    tweets.groupBy(_.createdAt).view.mapValues(_.size).toMap
  }
}
