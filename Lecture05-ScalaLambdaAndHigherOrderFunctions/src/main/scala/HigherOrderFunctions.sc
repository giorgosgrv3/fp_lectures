val words = List("apple", "banana", "orange", "grape")
val longWords = words.filter(_.length > 5) // Result: List("banana", "orange")
val longWords2 = words.filter(x => {x.length>5})
val nums = List(1, 2, 3, 4, 5)
val filterList = List(2, 4, 6)
val filtered = nums.filter(filterList.contains) // Result: List(2, 4)

case class Person(name: String, age: Int)
val people = List(Person("Alice", 30), Person("Bob", 15), Person("Charlie", 40))
val adults = people.filter(_.age >= 18) // Result: List(Person("Alice", 30), Person("Charlie", 40))
val adults2 = people.filter((x:Person) => {
  x.age>=18 && x.name.length>2
})
val adults3 = people.filter(_.age >= 18).filter(_.name.length>2)

val numbers = List(1, 2, 3, 4, 5, 10)
val doubled = numbers.map(_ * 2) // Result: List(2, 4, 6, 8, 10)
val squared = numbers.map((x:Int) => x * x) //inferred data types because it is inside a higher order function
val squares = numbers.map { //recall...you can use {} instead of () for a single parameter
  case n if n % 2 == 0 => n * n // Square even numbers
  case _ => 0 // Return 0 for odd numbers
}
val squares2 = numbers.map { //recall...you can use {} instead of () for a single parameter
  (n:Int) => n match{
    case n if n % 2 == 0 => n * n // Square even numbers
    case _ => 0 // Return 0 for odd numbers
  }
}
// Using the curried lambda function with map
/* This is a function that receives a list element x and returns a
function which receives an index of the list (y=2  is the third element of the list)
 and returns a function that receives
a z:Double and returns x*y*z, i.e., the curried function is applied on a list index (2) recieving 2 parameters*/
val curriedProduct = numbers.map(
  x => (y: Int) => (z: Double) => x * y * z)(2)(4)(3.4)

val adultNames = people.filter(_.age >= 18)
              .map(_.name) // Result: List
// Suppose you have a list of numbers where each number is repeated twice
val repeatedNumbers = List(List(1, 1), List(2, 2), List(3, 3), List(4, 4), List(5, 5))
// You can flatten it using flatMap and then double each element
val flattenedAndDoubled = repeatedNumbers.flatMap(list => list.map(_ * 2))

// Suppose you want to generate a list of all possible combinations of names for people with age greater than or equal to 18
val nameCombinations =
  people.flatMap(person => people.filter(_.age >= 18).map(_.name + " and " + person.name))


// You can flatten it using flatMap and then double each element
val flattenedAndDoubledAndReduced = repeatedNumbers.flatMap(
  list => list.map(_ * 2)
).reduce(_+_)

// Create a list of tuples containing each person's name and age, filter out those who are at least 18 years old,
// extract only the names, and concatenate them with "likes" in between using reduce
val whoFanciesWho =
people.map(person => Tuple2(person.name, person.age))
  .filter(_._2 >= 18)
  .map(_._1)
  .reduce(_ + " likes " + _)


// Create a larger list of people with some sharing the same initials
val morePeople = List(  Person("Alice", 30), Person("Bob", 25), Person("Charlie", 40), Person("Catherine", 35),   Person("David", 28), Person("Diana", 32), Person("Ella", 27), Person("Eleanor", 33),  Person("Eva", 29), Person("Frank", 45), Person("Frederick", 50), Person("Gina", 36),  Person("Grace", 42), Person("Hannah", 38), Person("Holly", 31), Person("Isaac", 40),  Person("Isabel", 29), Person("James", 34), Person("John", 48), Person("Joseph", 39),  Person("Julia", 41), Person("Katherine", 37), Person("Kate", 27), Person("Liam", 26),  Person("Luke", 33), Person("Matthew", 44), Person("Megan", 30), Person("Nathan", 35))

// Group the people by the first letter of their name
val groupedPeople = morePeople.groupBy(_.name.charAt(0))

// Compute the average age per group
val averageAges = groupedPeople.map {
  case (initial, people) =>
    val (totalAge, totalPeople) = people.foldLeft((0, 0)) {
      case ((accAge, accPeople), person) => (accAge + person.age, accPeople + 1)
    }
    val averageAge = if (totalPeople != 0) totalAge.toDouble / totalPeople else 0
    (initial, averageAge)
}

//ascending - default order
val ascendingSortedAverageAges = averageAges.toList.sortBy(_._2)
//descending order using "-"
val descendingSortedAverageAges = averageAges.toList.sortBy(-_._2)

// Print the average age for each group
groupedPeople.foreach { case (initial, averageAge) =>  println(s"Average age for people with names starting with $initial: $averageAge")}

// Sort average ages in descending order based on average age\
descendingSortedAverageAges.foreach { tuple =>
  tuple match {
    case (initial, avgAge) =>
      println(s"Initial: $initial, Average Age: $avgAge")
    case _ =>
      println("Tuple is null or has invalid format")
  }
}

/*********************sets******************/

// Filter even numbers
val evens = numbers.filter(_ % 2 == 0) // Result: HashSetSet(2, 4)

// Map each number to its square
val Squares = numbers.map(x => x * x) // Result: HashSet(1, 4, 9, 16, 25)

// FlatMap each number to its duplicate
val duplicates = numbers.flatMap(x => Set(x, x)) // Result: HashSet(1, 2, 3, 4, 5)

// Reduce to find the sum of numbers
val sum133 = numbers.reduce(_ + _) // Result: 15

// GroupBy even and odd numbers
val groupedNumbers = numbers.groupBy(_ % 2 == 0) // Result: Map(false -> Set(1, 3, 5), true -> Set(2, 4))

/*********************maps******************/
val ages = Map("Alice" -> 30, "Bob" -> 25, "Charlie" -> 35)

// Filter people older than 30
val olderThanThirty = ages.filter { case (_, age) => age > 30 } // Result: Map("Charlie" -> 35)

// Map each age to its square
val squaredAges = ages.map { case (name, age) => name -> (age * age) } // Result: Map("Alice" -> 900, "Bob" -> 625, "Charlie" -> 1225)

// FlatMap each age to its duplicate
val duplicateAges = ages.flatMap { case (name, age) => Map(name -> age, s"$name Duplicate" -> age) } // Result: Map("Alice" -> 30, "Alice Duplicate" -> 30, "Bob" -> 25, "Bob Duplicate" -> 25, "Charlie" -> 35, "Charlie Duplicate" -> 35)

// Reduce to find the sum of ages
val sumOfAges = ages.values.reduce(_ + _) // Result: 90

// GroupBy ages into two groups: <30 and >=30
val groupedAges = ages.groupBy { case (_, age) => age < 30 } // Result: Map(false -> Map("Alice" -> 30, "Charlie" -> 35), true -> Map("Bob" -> 25))

