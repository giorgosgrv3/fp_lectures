val originalPrices: Map[String, Double] = Map("item1" -> 10, "item2" -> 100, "item3" -> 1000)
val discount: Double = 10
val taxRate: Double = 5

def calculateDiscountedPrice(originalPrice: Double, discount: Double): Double = originalPrice * (1 - discount / 100)

def calculateFinalPrice(discountedPrice: Double, taxRate: Double): Double = discountedPrice * (1 + taxRate / 100)

// Functional but Naive Approach with Map
/** !!!In Scala, when you iterate over a Map using the map HOF, each element is represented
 * as a tuple (key, value), where _1 represents the key and _2 represents the value. !!!*/
val finalPricesNaive = originalPrices.map { entry =>
  val discountedPrice: Double = calculateDiscountedPrice(entry._2, discount)
  calculateFinalPrice(discountedPrice, taxRate)
}

// Functional Approach with Map
val discountedPricePaf: Double => Double = calculateDiscountedPrice(_, discount)
val finalPricePaf: Double => Double = calculateFinalPrice(_, taxRate)
val finalPrice: Double => Double = discountedPricePaf andThen finalPricePaf

val finalPrices = originalPrices.map { entry =>
  val discountedPrice: Double = discountedPricePaf(entry._2)
  val finalPriceValue: Double = finalPricePaf(discountedPrice)
  entry._1 -> finalPriceValue
}

// Let's define another partially applied function that adds a markup to the original price.
val markUp: Double = 6
def calculateMarkUp(originalPrice: Double, markUp: Double): Double = originalPrice * (1 + markUp / 100)
val calculateMarkUpPaf: Double => Double = calculateMarkUp(_, markUp)

val finalPriceWithMarkUp: Double => Double = calculateMarkUpPaf andThen finalPricePaf

val finalPricesWithMarkUp = originalPrices.map { entry =>
  val finalPriceValue: Double = finalPriceWithMarkUp(entry._2)
  entry._1 -> finalPriceValue
}

/** MORAL:
 * Modularity
 * Reusability
 * Composability
 * Flexibility*/
