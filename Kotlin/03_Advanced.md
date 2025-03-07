# Advanced Kotlin

## Lists

### Immutable Lists

Cannot be changed-

```kotlin
val shoppingList = listOf("Processor", "RAM", "Graphic Cards", "SSD", "HD")
```

### Mutable Lists

Can be changed.

```kotlin
val mutableList = mutableListOf("Processor", "RAM", "Graphic Cards", "SSD", "HD")
println(mutableList)
```

### List Methods(common ones)

Add an item

```kotlin
mutableList.add(2,"CPU") // This will add item at index 2(3rd place)

// or
mutableList.add("CPU")

```

Remove item from an index

```kotlin
mutableList.removeAt(2)
```

Replace item at index

```kotlin
mutableList[2] = "RTX 3090"
```

Get Item at an index

```kotlin
mutableList[2]
```

Check if item is in the list(will return true or false)

mutableList.contains("RAM")

---

## For Loop

```kotlin
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
```