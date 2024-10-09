fun main(){
 creatingList()
    forLoop()
}

// Lists
fun creatingList(){
    // Creating Normal List - Cannot be changed
    val shoppingList = listOf("Processor", "RAM", "Graphic Cards", "SSD", "HD")

    // Creating Mutable List - One that can be changed

    val mutableList = mutableListOf("Processor", "RAM", "Graphic Cards", "SSD", "HD")
    println(mutableList)

    mutableList.add(2,"CPU")
    println(mutableList)


    // Remove an index
    mutableList.removeAt(2)
    println(mutableList)

    // Replace and Get Item at some index
    mutableList[2] = "RTX 3090"
    println(mutableList[2])

    // Check if it contains

    println(mutableList.contains("RAM"))


}

// For Loops

fun forLoop(){
    val mutableList = mutableListOf("Processor", "RAM", "Graphic Cards", "SSD", "HD")

    for(item in mutableList){
        println(item)
    }

    // Breaking loop

    for(item in mutableList){
        println(item)
        if(item == "RAM"){

            break
        }
    }

    // using index in loop

    for (item in 0 until mutableList.size){
        println(item)
        println(mutableList[item])
    }
}
