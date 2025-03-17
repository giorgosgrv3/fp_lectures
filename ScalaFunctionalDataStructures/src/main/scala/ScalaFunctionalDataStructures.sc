//////////////     Scala Seq     ///////////////

val aSequence: Seq[Int] = Seq(1, 2, 2, 3) // Seq.apply(1,2,2,3) is equivalent
val aSequenceApply = Seq.apply(1,2,2,3)
val accessedElement = aSequence(1) // the element at index 1 is 2

val size = aSequence.length
val empty = aSequence.isEmpty
val asString = aSequence.mkString(", ")
val modifiedSeq = aSequence.updated(2, 42)
val noNegatives = aSequence.filterNot(_ < 2) // filter -> keep
val appendedSeq = aSequence :+ 10 //append (at end)
val prependedSeq = 10 +: aSequence //prepend (at start)
val addedSeq = aSequence.map(_ + 10) //add 10 to all elements
val firstThree = aSequence.take(2) //extract 2 from beginning
val dropThree = aSequence.drop(2) // skip 2 from beginning
val reversedSeq = aSequence.reverse
val uniqueSeq = aSequence.distinct //remove duplicate elements

////////////    Scala List    ////////////////

val aList = List(1,2,3,4,5)
val firstElement = aList.head   // keep only first element
val rest = aList.tail           // keep all but first element
val aPrependedList = 0 +: aList// List(0,1,2,3,4,5)
val anExtendedList = 0 +: aList :+ 6 // List(0,1,2,3,4,5,6)
/*
:: can only be used to prepend ONLY in List
+: can be used in both
HOWEVER, they differ in usage.
Anything that ends in : is right-associative (+: , ::), meaning x::xs is evaluated as xs.::x (prepend x to xs)
Everything else is left-associative (:+) , expression seq :+ elem is seq.:+(elem)
*/


List(1,2) ::: List(3,4) // List(1,2,3,4)
List(1,2) :: List(3,4) // List(List(1,2), 3, 4)
/*
::: JOINS two lists, into a new list
:: Prepends List(1,2) to List(3,4), making List(List(1,2),3,4)
*/

val lsize = aList.length //LISTS have both .length and .size
val lempty = aList.isEmpty
val lasString = aList.mkString(", ")
val lmodifiedSeq = aList.updated(2, 42) // updated(INDEX, element), so update index 2 with element 42
val reversedList = aList.reverse
val uniqueList = aList.distinct

val head = List(1,2,3,4,5).head
val tail = List(1,2,3,4,5).tail //traditional way
val head1 :: tail1 = (List(1, 2, 3, 4, 5): @unchecked) // alternative way
val initList = List(1, 2, 3, 4, 5).init // Returns all elements except the last one: List(1, 2, 3)

val lnoNegatives: List[Int] = aList.filterNot(_ < 2)
val lnoNegatives2: List[Int] = aList.filter(_<2)
val lfirstThree = aList.take(3) //keep 3 first
val ldropThree = aList.drop(3) //drop 3 first
val takeWhileList = List(1, 2, 3, 4, 5).takeWhile(_ < 3) // Returns List(1, 2)
val takeDropList = List(1, 2, 3, 4, 5).dropWhile(_ < 3) // Returns List(3, 4, 5)
val (prefix, suffix) = List(1, 2, 3, 4, 5).span(_ < 3) // Splits list into two, according to a condition


/////////////////     Scala Range     ////////////////////////

val aRange: Range = 1 to 3 by 2 // Range from 1 to 3 (inclusive) with step 2
val exclusiveRange = 1 until 5 // Exclusive of 5 (1 to 4)

val accessedElementR = aRange(1) // the element at index 1 is 3
val sizeR = aRange.length // Size of the range
val emptyR = aRange.isEmpty // Check if the range is empty (always false for a non-empty range)
val asStringR = aRange.mkString(", ") // Convert the range into a string with a specified separator
val modifiedRangeR = aRange.updated(1,42)

val noNegativesR = aRange.filter(_ >= 2) //filter elements >=2
// Appending and prepending elements (Note: Ranges don't support :+ or +: directly)
val appendedRange = aRange ++ Seq(4, 5, 6) //this is a Vector
val appendedRange2 = Seq(4, 5, 6) ++ aRange // order matters, this is now a List
val appendedRange3 = Set(4, 5, 6) ++ aRange // order matters, this is now a HashSet
val appendedRange4 = aRange ++ Set(4,5,6) // order matters, this is now a Vector
val appendedRange5 = aRange ++ Map(1 -> "one") //also a vector


val prependedRange = Seq(0) ++ aRange
val mixedRange = Seq(0) ++ aRange ++ Seq(5,6,7)
// Taking and dropping elements
val firstThreeR = aRange.take(3)
val dropThreeR = aRange.drop(3)
val reversedRangeR = aRange.reverse // Reversing the order of elements (Note: Ranges don't support reverse directly)
val distinctRangeR = aRange.distinct // Distinct elements (Note: Ranges don't support distinct directly)


//Scala Set
val aSet: Set[Int] = Set(1, 2, 3)
val containsTwo = aSet.contains(2) // true

val setSize = aSet.size //set only supports .size, not .length
val isSetEmpty = aSet.isEmpty
val setString = aSet.mkString(", ")
val unionSet = aSet ++ Set(3, 4, 5) // HashSet(1, 2, 3, 4, 5)
/*
Notice on the right, that aSet gave us a Set(1,2,3)
aSet ++ Set(3,4,5) gave us a HashSet(1,2,3,4,5). WHY DID THIS HAPPEN?
Because for small number of elements (<=4), sets become Set1, Set2, Set3, Set4 (companion objects) for efficiency.
For larger number (>4), it defaults to hashSet.
*/

val newSet = aSet + 4 // Set(1, 2, 3, 4)
val newSet2 = aSet ++ Set(4,5,6)
val removedTwo = aSet - 2 // Set(1, 3)
val diffSet = aSet -- Set(2, 3) // Set(1)
val commonElements = aSet & Set(2, 3, 4) // Set(2, 3)
val isSubset = Set(1, 2) subsetOf aSet // true

val mySet: Set[Int] = Set(1, 2, 3)
mySet match {
  //case s1 if s1.contains(1) => println("Contains 1.")
  case s2 if s2.contains(2) => println("Contains 2") //s2 is a temporary variable
  case _ => println("Does not contain 2")
}

///////////// SCALA MAP ////////////////////////
val myMap: Map[String, Int] = Map("one" -> 1, "two" -> 2, "three" -> 3)
val mapSize = myMap.size //map only supports .size, not .length
val isMapEmpty = myMap.isEmpty


val mapKeys = myMap.keys.toList
/* The following command returns an Iterable. But Iterable is a trait! According to the class/trait hierachy
on the slide, this has a default implementation to Seq which has a default implementation to List. 
But if you give "myMap.values.getClass" you will get a "class scala.collection.MapOps$$anon$1". Why?
Because lists assume some ordering on the values, while Map has no such ordering. Therefore, the Scala compiler
creates an $$anon$1 (i.e., anonymous) class for something it cannot cast by default.*/
val mapValues = myMap.values
myMap.values.getClass


val mapValues2 = myMap.values.toList
val stringValues = myMap.values.mkString(";")
val stringKeys = myMap.keys.mkString(";")
val valueOfTwo = myMap("two") // 2

val newMap = myMap + ("four" -> 4) //Map4
val newMap2 = myMap ++ Map ("four" -> 4, "five" -> 5) // >4 elements, HashMap
val newMap3 = myMap ++ Map ("four" -> 4, 5 -> "five" ) // 5 -> "five" INCOMPATIBLE FORMAT, becomes a List of tuples
val removedTwoM = Map("one" -> 1, "two" -> 2, "three" -> 3, "four" -> 4, "five" ->5) - "five" - "three"

val filteredMap = myMap.view.filterKeys(key => key.startsWith("t")).toMap
val aMap: Map[String, Int] = Map("one" -> 1, "two" -> 2, "three" -> 3)
aMap match {
  case m if m.contains("two") => println("Contains key 'two'")
  case _ => println("Does not contain key 'two'")
}

//Tuples
val tupleWithList: (Int, List[String]) = (42, List("Scala", "Collections"))
val tupleWithRevList: (Int, List[String]) = (42, List("Scala", "Collections").reverse)

val tupleWithMap: (String, Map[String, Int]) = ("Key", Map("one" -> 1, "two" -> 2))
val tupleWithSet: (String, Set[Double]) = ("SetKey", Set(3.14, 2.71, 1.0))
val tupleWithRange: (Int, Range) = (1, 1 to 5)

//Access tuple elements with <tupleName>._<index>
// <index> refers to SPECIFIC element from Tuple1-22, it's an index that starts at 0 like in Lists
val listElement = tupleWithList._2 // List("Scala", "Collections")
val mapElement = tupleWithMap._2 // Map("one" -> 1, "two" -> 2)

val anotherTupleWithList: (Double, List[String]) = (2.718, List("Functional", "Programming"))
val concatenatedTuple = tupleWithList ++ anotherTupleWithList
val tupleWithAddedElement: (Int, List[String], Double) = (tupleWithList._1, tupleWithList._2, 3.14)
val tupleWithoutElement: (String, Set[Double]) = tupleWithSet match {
  case (key, numberSet) => (key, numberSet - 3.14)
}
val tupleWithNestedList: (Int, List[List[String]]) = (42, List(List("Scala", "Collections"), List("Functional", "Programming")))
val nestedList: List[List[String]] = tupleWithNestedList._2
val firstInnerList: List[String] = nestedList.head
val modifiedTuple: (Int, List[List[String]]) = tupleWithNestedList.copy(_2 = nestedList :+ List("New", "List"))

val pair = 42 -> "fourty-two"
pair.getClass // class Scala.Tuple2
// so ESSENTIALLY, a Map is a collection of Tuple2 collections.

val list1 = List(1, 2, 3, 4)
val list2 = List("one", "two", "three", "four")
val zippedList: List[(Int, String)] = list1.zip(list2)
zippedList.head
zippedList.head._1
zippedList.head._2
zippedList(2)
zippedList(2)._1
zippedList(2)._2

//zipping lists - zipping goes up to the iterable with least elements
val oneList = List(1,2,3,4)
val twoList = List("one", "two", "three")
val zipped = oneList.zip(twoList) //returns List of tuples

//////////////////////       EXCEPTION HANDLING --> Option      /////////////////////
/*
Option is a container type (we will use it instead of declaring collection type), that represents
either a value (Some) or no value (None).
It's an abstract class with two concrete subclasses, Some and None.
Used to handle situations where value might be present or absent, helping avoid null references.
 */
val someValue: Option[Int] = Some(42) // Represents a value
val noValue: Option[Int] = None // Represents no value
val maybeValue: Option[Int] = Some(42)
/* 42 is the actual value being wrapped by Some. It could be any non-null value, Some is used to
indicate that the value is present. */


maybeValue match {
  case Some(value) => println(s"Value is present: $value") //side effect!
  case None        => () //side effect!
}

/*
fold() is a HOF, fundamental operation on many FP data types, including Option.
fold() takes 2 arguments, one is a start value, the other is a combining function.
if the result of the function is None, the start value will be returned.
*/
val result: String = maybeValue.fold("Default") { value =>
  s"The value is: $value"
}


// Function to retrieve stock price for a given symbol
def getStockPrice(symbol: String): Option[Double] = {
  val stockPrices: Map[String, Double] = Map(
    "AAPL" -> 150.0,
    "GOOGL" -> 2500.0,
    "MSFT" -> 300.0
  )

  stockPrices.get(symbol)
  /* WHAT IS THE DIFFERENCE BETWEEN myMap(2) and myMap.get(2)??
  myMap(2) returns the value of key "2" , but this key might not exist.
  In that case, we have an exception.
  myMap.get(2) returns an Option. If the key exists, we get Some(value).
  This takes care of the case where the key doesn't exist.
   */
}

// Function to format stock price result
def formatStockPriceResult(symbol: String, result: Option[Double]): String =
  result.fold(s"Could not retrieve the price for $symbol")(price =>
    s"The current price of $symbol is $$$price"
  )

// Example usage
val result1: String = formatStockPriceResult("AAPL", getStockPrice("AAPL")) // Valid stock symbol
val result2: String = formatStockPriceResult("TSLA", getStockPrice("TSLA")) // Invalid stock symbol

// Results (no printing)
val finalResult: List[String] = List(result1, result2)

// The final result can be used or processed further without any side effects
// (You can choose to print or use the result as needed in the broader context of your application)
// println(finalResult.mkString("\n"))

val testList:List[String] = List("a","b","c","d")//List(a, b, c, d)
val testList2:List[String] = testList.tail //List(b, c, d)

def concatenateItems(items: String*): String =
  items.mkString(" ")

// Example usage
val variadicResult1 = concatenateItems("Apple", "Banana", "Orange")
val variadicResult2 = concatenateItems("Java", "Scala", "Kotlin", "Python")

val num = List(2,3,4,5,6)

//REDUCE
val sum1 = num.sum
val sum2 = num.reduce(_+_) //same result
val sum3 = num.reduce((a,b)=> a+b) //same result

val product1 = num.product
val product2 = num.reduce(_*_) //same result
val product3 = num.reduce((a,b)=> a*b) //same result

// FOLD === FOLDLEFT
// fold(initialValue)((accumulatedValue, aggregatedValue) => anOperation)
// in the first round of fold, the initialValue is the accumulatedValue
// after each round, the result goes in accumulatedValue
val sum4 = num.fold(0)((a,b) => a+b)
val sum5 = num.fold(0)(_+_)
val sum6 = num.fold(10)(_+_)

val product4 = num.fold(1)(_*_)
val product5 = num.fold(2)(_*_) //doubles the product, initialValue is 2

val words = List("Hello", "Scala", "FP")
val sentence = words.foldLeft("")(_ + " " + _)

// FOLDRIGHT
// right is the same as left for associative operations (like + or *)
// they differ when the order of evaluation matters
// e.g. Subtraction

val numbers2 = List(1, 2, 3, 4)

// foldLeft: (((0 - 1) - 2) - 3) - 4
val left = numbers2.foldLeft(0)(_-_)

// foldRight: 1 - (2 - (3 - (4 - 0)))
val right = numbers2.foldRight(0)(_-_)


//COMBINED filter and map and fold
// List(1,2,3,4)
// map(_*10) ---> List(10, 20, 30, 40)
// filter(_ < 35) --> List(10, 20, 30)
// fold(0)(_+_) --> 10 + 20 + 30 = 60
val combined = numbers2.map(_*10).filter(_<35).fold(0)(_+_) //or .sum at the end
val combined2 = numbers2.map(x => x * 10 ).filter(x => x < 35 ).fold(0)((x,y) => x + y)