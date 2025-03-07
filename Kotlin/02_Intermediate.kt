
fun main(){

    // Calling our functions

    println("\nDefault Coffee \n")
    makeCoffee()
    println("\nCustom Coffee \n")
    makeCustomCoffee(size = "S", sugar = true)
    println("\nReturn Custom Coffee \n")
    val coffee = giveCustomCoffee(cupSize = "S", sugar = false, name = "Aditya")
    println(coffee)
    println("\n \n \n")
    useDogClass()
    println("\n \n \n")
    Coffee()
}

// Creating Functions

// Name is camel case - should define it.

// This function is not taking and parameter or returning anything. It is just processing data.
fun makeCoffee(){
    println("Getting Coffee Beans...")
    println("Grinding Beans...")
    println("Heating Water...")
    println("Adding coffee...")

}

// To have more control over functions, we insert parameters that can make functions more flexible.

fun makeCustomCoffee(size:String, sugar:Boolean, ){
    println("Getting Coffee Beans...")
    println("Grinding Beans...")
    when (size.uppercase()) {
        "S" -> {
            println("Heating Small Cup of Water...")
        }
        "M" -> {
            println("Heating Medium Cup of Water...")
        }
        "L" -> {
            println("Heating Large Cup of Water...")
        }
        else -> {
            println("Size not defined. Heating Medium Cup of Water...")
        }

    }

    if (sugar){
        println("Adding sugar...")
    }
    println("Adding coffee...")
}

// So far our functions are not returning anything. Let's make one that returns a statement about final coffee

fun giveCustomCoffee(cupSize:String, sugar:Boolean, name: String): String{
    println("Getting Coffee Beans...")
    println("Grinding Beans...")

    var size = ""
    size = when (cupSize.uppercase()) {
        "S" -> {
            "Small"
        }
        "M" -> {
            "Medium"
        }
        "L" -> {
            "Medium"
        }
        else -> {
            "Undefined(Medium)"
        }

    }

    println("Heating $size Cup of Water...")

    if (sugar){
        println("Adding sugar...")
    }
    println("Adding coffee...")

    return "A $size size coffee with ${if (sugar) "sugar" else "no sugar"} is prepared for $name"
}

// Class - A blueprint that is used to create objects

// Classes can have constructors, initializers, functions, etc.

class Dog(val name: String, val breed: String?, val age: Int = 2){

    init {
        bark(name)
    }

    fun bark(name: String){
        println("$name says woof woof")
    }
}

fun useDogClass(){
    var myDog = Dog(name = "Shadow", breed = "Retriever")
}

// Data Class - Used for storing data, they have some default functions

data class Coffee(var coffeeSize: String = "M", var sugar: Int = 2, var withCream: Boolean = true)



