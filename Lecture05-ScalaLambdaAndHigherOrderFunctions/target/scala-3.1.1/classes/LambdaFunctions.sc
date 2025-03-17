//defining the lambda function
//(x:Int, y:Int) => {x + y}
//even assigning it to a val
val f1: (Int,Int) => Int = (x:Int, y:Int) => {x + y}
//applying the lambda function
f1(2,3)
f1.apply(2,3)
f1 apply(2,3) //Surprise! you do not need the dot in Scala :)


val f2 = (x:Int, y:Int) => {x + y}

val f3:  (Int,Int) => Int = (x, y) => {x + y}

//val f31 = (x, y) => {x + y}

val f: Int => Int = x => x + 5
val g: Int => Int = x => x * 5
val h: Int => Int = f andThen g
val m: Int => Int = g compose f
val n: Int => Int = x => g apply {f apply(x)}
val p: Int => Int = x => g(f(x))
println(h(50))
println(m(50))
println(n(50))
println(p(50))

val f4: (Int, Int) => Int = _ + _
val f5: (Int, Int, Int) => Int = _ + _ + _

//val f4: (Int, Int) => Int = (x: Int, y: Int) => x + y
//val f5: (Int, Int, Int) => Int = (x: Int, y: Int, z: Int) => x + y + z


val concatenate: (String, String) => String = _ + _
val resultConcatenation = concatenate("Hello", "World")

val isEven: Int => Boolean = _ % 2 == 0
val number = 6
val resultIsEven = isEven apply(number)

val compareLength: (String, String) => Boolean = _.length > _.length
val string1 = "apple"; val string2 = "banana"
val resultCompareLength = compareLength(string1, string2)


val power: (Double, Double) => Double = math.pow(_, _)
val base = 2.0; val exponent = 3.0
val resultPower = power(base, exponent)

val compareString: (String, String) => Int = _.compareTo(_)
val stringA = "apple"; val stringB = "banana"
val resultCompareString = compareString(stringA, stringB)

type MyFunction = Function2[Int, Int, Int]
val f6: MyFunction = (x, y) => x + y //lambda function


//anonymous, but non-lambda
val f7: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
  def apply(x: Int, y: Int): Int = x + y
}

f7 apply(3,2)


val f8: (Int, Int) => Int = (x, y) => {
  def addition(a: Int, b: Int): Int = a + b
  addition.apply(x, y)
}
f8 apply(5,6)


val f9: Int => Int => Int = x => y => x + y

val f10: Int => Int => Double => String = x => y => z => {
  val result = x * y * z // Example computation
  s"The result is $result"
}
//f10(1,1,2.0)
f10(1)(10)(2.0)


val f101 = f10 apply(1)
val f102 = f101 apply(1)
val f103 = f102 apply(2.0)

val f11: Int => (Int => Int) = x => y => x + y
// Apply f11 to an integer x
val f11Applied: Int => Int = f11(10)
// Now apply the resulting function to an integer y
val result: Int = f11Applied(5)


val f12: Int => (Int, Int) => Int = x => (y, z) => x + y + z
val f121: (Int, Int) => Int = f12(2)
val sum121: Int = f121(3, 5)
//val sum123a:Int = f121(3, _) //gives error due to explicit type declaration :Int
val sum123b = f121(3, _)
val sum123c = f121(_, 10)
val sum123d = f121(_, _)
val sum124 = sum123b(5)

val f12Curried: Int => Int => Int => Int = x => y => z => x + y + z
val curriedResult = f12Curried(2)(3)(5)

def addPAF(x: Int, y: Int): Int = x + y
val addPAFThree = addPAF(3, _: Int)
val addPAFThree2 = addPAF(3, _)

def addCurried(x: Int)(y: Int): Int = x + y
val addCurriedThree = addCurried(3)




