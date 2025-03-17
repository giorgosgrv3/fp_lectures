import impure.FileReader
import pure.TweetAnalytics

object Main {
  def main(args: Array[String]): Unit = {
    val filename = "data/tweetsSmall.json" // Change this to your file name
    val resultJson = FileReader.readJsonFromFile(filename)

    resultJson match {
      case Some(json) =>
        val parsedTweets = pure.TweetAnalytics.parseTweets(json)

        // Compute analytics results
        val result1 = pure.TweetAnalytics.tweetsPerUser(parsedTweets)
        val result2 = pure.TweetAnalytics.topUser(parsedTweets)
        val result3 = pure.TweetAnalytics.averageTweetLength(parsedTweets)
        val result4 = pure.TweetAnalytics.tweetsByDay(parsedTweets)
        println("FUCKJ YAAAAH")

      case None =>
        println("Failed to read JSON from file.")
    }
  }
}
