# Kotlin Basics

Kotlin is a statically typed, object-oriented programming language that is interoperable with the Java virtual machine (JVM), Java Class Libraries and Android . The Kotlin programming language was originally designed to improve the Java programming language and is often used in conjunction with Java.

## Main Function

Main function is the starting point of any kotlin program.

```kotlin
fun main() {
    println("Hello, world!!!")
}
```
---

## Variables

Variables are used to store data which will be available to users while the program is running. 

There are two types of variables - val and var

val is a constant value that cannot be changed

```kotlin
 val pi = 3.14

// pi = 3.15 - This will show an error
```

var is changeable variable

```kotlin
var changeAble = 10
changeAble = 15
```

---

## Data Types

val and var assign data types by default, but if we want to specify it, we can use one of these:-

### Integer Data Type - Byte, Short, Int, Long. default = Int

```kotlin
val smallInteger1 = 32 // This is Int data type

val smallInteger2: Byte = 32 // This is Byte data type

println(smallInteger1::class) // To print the data type
println(smallInteger2::class)
```

### Decimals - float, double. default = double

```kotlin
val smallDecimal1 = 3.24 // double data type

val smallDecimal2 = 3.24f // float data type

val smallDecimal3: Float = 3.24F // float data type

println(smallDecimal1::class)
println(smallDecimal2::class)
println(smallDecimal3::class)
```

### Unsigned Integers

They cut off the negative parts and double the amount in positive direction.

They can only have positive numbers. - UByte, UShort, UInt, ULong. 

```kotlin
val unsignedNumber: UShort = 44u

println(unsignedNumber::class)
```

### Boolean - Contains true, false or null

```kotlin
val myTrue = true
val myFalse = false

println(myTrue::class)
println(myFalse::class)
```

### Char

For storing single characters in single quotes.

```kotlin
val myChar = 'a'

println(myChar::class)

// We can also use Unicode with backslash

val anotherChar = '\u00AE'
```

### String 

A collection of character, represented by double quotes.

```kotlin
val myString = "This is a String"

println(myString::class)
```

---

## Logical Operators 

Boolean data type can be used with logical operators which are || , && , !

### && - Logical AND - if both are true then it will return true

```kotlin
val logicalAnd1 = true && true // will return true
val logicalAnd2 = true && false // will return false
val logicalAnd3 = false && true // will return false
val logicalAnd4 = false && false // will return false

println(logicalAnd1)
println(logicalAnd2)
println(logicalAnd3)
println(logicalAnd4)
```

### || - Logical OR - Will return true if either one is true

```kotlin
val logicalOr1 = true || true // will return true
val logicalOr2 = true || false // will return true
val logicalOr3 = false || true // will return true
val logicalOr4 = false || false // will return false

println(logicalOr1)
println(logicalOr2)
println(logicalOr3)
println(logicalOr4)
```

### ! - Logical NOT - Will return the opposite

```kotlin
val logicalNot1 = !true // Will return false
val logicalNot2 = !false // Will return true

println(logicalNot1)
println(logicalNot2)
```

---

## Conditional Statements

If true do this, otherwise do that

```kotlin
println("Enter your age in whole number")
val userAgeString = readln() // to get user input - only returns string data type
val userAgeInt = userAgeString.toInt() // Type cast

if(userAgeInt > 18){
    println("You are an Adult.")
}else if (userAgeInt < 18 && userAgeInt > 12){ //(userAgeInt < 18 && userAgeInt > 12) can also be written as (userAgeInt in 13..18)
    println("You are a teenager.")
}else{
    println("You are a child.")
    }
```

--- 

## While Loop 

Do something as long as the condition is true.

```kotlin
var num = 3
    while (num < 5){
        println("Number is $num")
        num++
    }
```

---

## Project - Rock, Paper, Scissors

```kotlin
import java.util.*

// Rock Paper Scissors

fun main(){
    game()
}

fun game(){
    var computerChoice = ""
    var playerChoice = ""

    println("Choose R - Rock, P - Paper, S - Scissors")

    playerChoice = readln().uppercase()

    val randomNumber = (1..3).random() // Will pick any random value from 1 to 3(including 3 and 1)

    if(randomNumber == 1){
        computerChoice = "R"
    }else if(randomNumber == 2){
        computerChoice = "P"
    }else{
        computerChoice = "S"
    }

    // Or

//    computerChoice = when (randomNumber) {
//        1 -> {
//            "R"
//        }
//        2 -> {
//            "P"
//        }
//        else -> {
//            "S"
//        }
//    }

    if(playerChoice == computerChoice){
        println("It's a tie. You both choose $computerChoice")
        game()
    }else if (playerChoice == "R" && computerChoice == "P"){
        println("Computer Won - Paper covers rocks!!!")
        game()

    }else if(playerChoice == "R" && computerChoice == "S"){
        println("You Won. Rock breaks scissors.")
        game()

    }else if(playerChoice == "P" && computerChoice == "R"){
        println("You Won. Paper covers rock.")
        game()

    }else if (playerChoice == "P" && computerChoice == "S"){
        println("Computer Won. Scissors cut paper!!!.")
        game()

    }else if (playerChoice == "S" && computerChoice == "R"){
        println("Computer Won. Rock breaks scissors!!!")
        game()

    }else if (playerChoice == "S" && computerChoice == "P"){
        println("You won. Scissors cut paper.")
        game()

    }else{
        println("Wrong input. Try again")
        game()
    }
}




```