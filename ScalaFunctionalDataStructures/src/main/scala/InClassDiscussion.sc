def getStockPrice(symbol: String): Option[Double] = {
  val stockPrices: Map[String, Double] = Map(
    "AAPL" -> 150.0,
    "GOOGL" -> 2500.0,
    "MSFT" -> 300.0
  )

  stockPrices.get(symbol)
}

/* This is the most idiomatic way*/
val odysseasApproach = getStockPrice("AAPL") match
  case Some(x) => x
  case _ => 0D

val list = List(1, 2, 3, 4, 5)
val sum = list.fold(0)((x, y) => x + y)
val elementToRemove = 1
val newList = list.filter(_ != elementToRemove)
list.fold(0)((x, y) => x + y) == newList.fold(1)((x, y) => x + y)
