package org.wit.homebrew

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    logger.info { "Launching Homebrew Helper Console App" }
    println("Homebrew Helper Kotlin App Version 2.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addHomebrew()
            2 -> updateHomebrew()
            3 -> listHomebrews()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Homebrew Helper Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("MAIN MENU")
    println(" 1. Add a Homebrew")
    println(" 2. Update a Homebrew")
    println(" 3. List All Homebrews")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addHomebrew(){
    var beerName : String
    var beerStyle : String
    var anotherMalt : Boolean
    var nextMalt : Int
    var maltChoice : String
    var malt1 : String
    val malts : MutableList<String> = mutableListOf()
    var boilLength : Int
    var anotherHop : Boolean
    var nextHop : Int
    var hopChoice : String
    var hop1 : String
    var hopTime1 : Int
    val hops : MutableList<String> = mutableListOf()
    val hopTimes : MutableList<Int> = mutableListOf()
    var yeast : String
    var origGrav : Int
    var finalGrav : Int
    var dryHop : String
    var dryHopType : String
    var dryHopLength : Int

    anotherMalt = true
    anotherHop = true
    nextMalt = 0
    nextHop = 0

    println("You Chose to Add a New Homebrew")
    println()
    print("Enter the beer name : ")
    beerName = readLine()!!
    println()
    print("Enter the beer style : ")
    beerStyle = readLine()!!
    println()
    print("Enter the first malt used : ")
    malt1 = readLine()!!
    println()
    while (anotherMalt != false) {
        while (nextMalt < 3) {
            print("Would you like to enter another malt? (y/n) : ")
            maltChoice = readLine()!!
            if (maltChoice.equals("n")) {
                anotherMalt = false
                break
            } else {
                println()
                print("Enter the next malt used : ")
                malts.add(readLine()!!)
                println()
                nextMalt++
            }
        }
    }
    println()
    print("Enter the boil length in minutes : ")
    println()
    boilLength = readInt()!!
    println()
    print("Enter the first type of hop used : ")
    hop1 = readLine()!!
    println()
    print("Enter when $hop1 was added to the boil : ")
    hopTime1 = readInt()!!
    println()
    while (anotherHop != false) {
        while (nextHop < 3) {
            print("Would you like to enter another hop? (y/n) : ")
            hopChoice = readLine()!!
            if (hopChoice.equals("n")) {
                anotherHop = false
                break
            } else {
                println()
                print("Enter the next hop used : ")
                hops.add(readLine()!!)
                println()
                nextHop++
                print("Enter when the hop was added to the boil : ")
                hopTimes.add(readInt()!!)
                println()
            }
        }
    }
    println()
    print("Enter the yeast name : ")
    yeast = readLine()!!
    println()
    print("Enter the measured Original Gravity : ")
    origGrav = readInt()!!
    println()
    print("Enter the measured Final Gravity : ")
    finalGrav = readInt()!!
    println()
    print("Did you dry hop? (y/n) : ")
    dryHop = readLine()!!
        if (dryHop != "n") {
            println()
            print("Enter the hop type used to dry hop : ")
            dryHopType = readLine()!!
            println()
            print("How long did you dry hop : ")
            dryHopLength = readInt()!!
            println()
        }
}

fun updateHomebrew() {
    println("You Chose to Update a Homebrew")
}

fun listHomebrews() {
    println("You Chose to List All Homebrews")
}

fun readInt() = readLine()!!.toInt()