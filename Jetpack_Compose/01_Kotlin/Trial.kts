import java.awt.Color

data class Car(
    var engineType: String,
    var numberOfDoors: Int,
    var noOfSeats: Int,
    var color: Color
)

class CarFactory(var car: Car){
    fun printEngine(){
        println(car.engineType)
    }
}

var sportsCar = Car(
    engineType = "Electric",
    numberOfDoors = 2,
    noOfSeats = 2,
    color = Color.BLACK
)

var familyCar = Car(
    engineType = "Electric",
    numberOfDoors = 6,
    noOfSeats = 9,
    color = Color.BLACK
)

var normalCar = Car(
    engineType = "Diesel",
    numberOfDoors = 4,
    noOfSeats = 4,
    color = Color.BLACK
)

println(familyCar.engineType)

var myCarFactory = CarFactory(sportsCar)

myCarFactory.printEngine()

