// Sample JSON dataset of tweets
val tweets: List[String] = List(
  """{"text": "Just posted a new blog post on Scala programming. Check it out!", "created_at": "2024-04-10", "user": "user1"}""",
  """{"text": "Excited to announce our upcoming webinar on functional programming with Scala.", "created_at": "2024-04-09", "user": "user2"}""",
  """{"text": "Attending the Scala conference next week. Looking forward to it!", "created_at": "2024-04-08", "user": "user3"}""",
  // More tweets...
)

import io.circe._
import io.circe.parser._

// Case class representing a tweet
case class Tweet(text: String, createdAt: String, user: String)

// Parse JSON tweets into a case class
def parseTweets(jsonTweets: List[String]): List[Tweet] = {
  /*In Scala, when you use flatMap on a collection of Option values,
   it will flatten the results while discarding any None values.*/
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

// Functional analytics functions

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
//remember, i despise ifs
/*def averageTweetLength(tweets: List[Tweet]): Double = {
  val totalCharacters = tweets.map(_.text.length).sum.toDouble
  if (tweets.nonEmpty) totalCharacters / tweets.size else 0
}*/

// Groups tweets by the day they were created
def tweetsByDay(tweets: List[Tweet]): Map[String, Int] = {
  tweets.groupBy(_.createdAt).view.mapValues(_.size).toMap
}

// Usage

// Parse tweets from JSON strings
val tweetsJson: List[String] = tweets
val parsedTweets: List[Tweet] = parseTweets(tweetsJson)

// Compute analytics results
val result1 = tweetsPerUser(parsedTweets)
val result2 = topUser(parsedTweets)
val result3 = averageTweetLength(parsedTweets)
val result4 = tweetsByDay(parsedTweets)
