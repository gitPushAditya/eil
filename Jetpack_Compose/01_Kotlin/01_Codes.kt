// Main Function

fun main(){
    println("Hello World!!!")

    variables()
    dataTypes()
    logicalOperators()
    whileLoop()
}

// Variables

fun variables(){
    // Basically two types of variables- val and var

    // val is a constant value that cannot be changed

    val pi = 3.14

    // pi = 3.15 - This will show an error

    // var is changeable variable

    var changeAble = 10
    changeAble = 15

    println(pi)
    println(changeAble)
}

// Data Types

fun dataTypes(){

    // val and var assign data types by default, but if we want to specify it, we can use one of these:-

    // Integer Data Type - Byte, Short, Int, Long. default = Int

    val smallInteger1 = 32 // This is Int data type

    val smallInteger2: Byte = 32 // This is Byte data type

    println(smallInteger1::class) // To print the data type
    println(smallInteger2::class)



    // Decimals - float, double. default = double

    val smallDecimal1 = 3.24 // double data type

    val smallDecimal2 = 3.24f // float data type

    val smallDecimal3: Float = 3.24F // float data type

    println(smallDecimal1::class)
    println(smallDecimal2::class)
    println(smallDecimal3::class)

    // Unsigned Integers - They cut off the negative parts and double the amount in positive direction.
    //  They can only have positive numbers. - UByte, UShort, UInt, ULong

    val unsignedNumber: UShort = 44u

    println(unsignedNumber::class)


    // Boolean - Contains true, false or null

    val myTrue = true
    val myFalse = false

    println(myTrue::class)
    println(myFalse::class)

    // Char - For storing single characters in single quotes

    val myChar = 'a'

    println(myChar::class)

    // We can also use Unicode with backslash

    val anotherChar = '\u00AE'

    // String - A collection of character, represented by double quotes

    val myString = "This is a String"

    println(myString::class)

}


// Logical Operators

fun logicalOperators(){
    // Boolean data type can be used with logical operators which are || , && , !

    // && - Logical AND - if both are true then it will return true

    val logicalAnd1 = true && true // will return true
    val logicalAnd2 = true && false // will return false
    val logicalAnd3 = false && true // will return false
    val logicalAnd4 = false && false // will return false

    println(logicalAnd1)
    println(logicalAnd2)
    println(logicalAnd3)
    println(logicalAnd4)

    // || - Logical OR - Will return true if either one is true

    val logicalOr1 = true || true // will return true
    val logicalOr2 = true || false // will return true
    val logicalOr3 = false || true // will return true
    val logicalOr4 = false || false // will return false

    println(logicalOr1)
    println(logicalOr2)
    println(logicalOr3)
    println(logicalOr4)

    // ! - Logical NOT - Will return the opposite

    val logicalNot1 = !true // Will return false
    val logicalNot2 = !false // Will return true

    println(logicalNot1)
    println(logicalNot2)

}

// Conditional Statements - if true do this, otherwise do that

fun  conditionalStatements(){
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
}

// While Loop

fun whileLoop(){
    var num = 3
    while (num < 5){
        println("Number is $num")
        num++
    }
}