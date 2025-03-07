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



