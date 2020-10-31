package org.wit.homebrew.main

import mu.KotlinLogging
import org.wit.homebrew.models.HomebrewModel

private val logger = KotlinLogging.logger {}
var homebrew = HomebrewModel()

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
    homebrew.anotherMalt = true
    homebrew.anotherHop = true
    homebrew.nextMalt = 0
    homebrew.nextHop = 0

    println("You Chose to Add a New Homebrew")
    println()
    print("Enter the beer name : ")
    homebrew.beerName = readLine()!!
    println()
    print("Enter the beer style : ")
    homebrew.beerStyle = readLine()!!
    println()
    print("Enter the first malt used : ")
    homebrew.malt1 = readLine()!!
    println()
    while (homebrew.anotherMalt != false) {
        while (homebrew.nextMalt < 3) {
            print("Would you like to enter another malt? (y/n) : ")
            homebrew.maltChoice = readLine()!!
            if (homebrew.maltChoice.equals("n")) {
                homebrew.anotherMalt = false
                break
            } else {
                println()
                print("Enter the next malt used : ")
                homebrew.malts.add(readLine()!!)
                println()
                homebrew.nextMalt++
            }
        }
    }
    println()
    print("Enter the boil length in minutes : ")
    println()
    homebrew.boilLength = readInt()!!
    println()
    print("Enter the first type of hop used : ")
    homebrew.hop1 = readLine()!!
    println()
    print("Enter when [ " + homebrew.hop1 + " ] was added to the boil : ")
    homebrew.hopTime1 = readInt()!!
    println()
    while (homebrew.anotherHop != false) {
        while (homebrew.nextHop < 3) {
            print("Would you like to enter another hop? (y/n) : ")
            homebrew.hopChoice = readLine()!!
            if (homebrew.hopChoice.equals("n")) {
                homebrew.anotherHop = false
                break
            } else {
                println()
                print("Enter the next hop used : ")
                homebrew.hops.add(readLine()!!)
                println()
                homebrew.nextHop++
                print("Enter when the hop was added to the boil : ")
                homebrew.hopTimes.add(readInt()!!)
                println()
            }
        }
    }
    println()
    print("Enter the yeast name : ")
    homebrew.yeast = readLine()!!
    println()
    print("Enter the measured Original Gravity : ")
    homebrew.origGrav = readInt()!!
    println()
    print("Enter the measured Final Gravity : ")
    homebrew.finalGrav = readInt()!!
    println()
    print("Did you dry hop? (y/n) : ")
    homebrew.dryHop = readLine()!!
        if (homebrew.dryHop != "n") {
            println()
            print("Enter the hop type used to dry hop : ")
            homebrew.dryHopType = readLine()!!
            println()
            print("How long did you dry hop : ")
            homebrew.dryHopLength = readInt()!!
            println()
        }
}

fun updateHomebrew() {
    println("You Chose to Update a Homebrew")
    println()
    print("Enter a new Beer Name for [ " + homebrew.beerName + " ] : ")
    homebrew.beerName = readLine()!!
    print("Enter a new Beer Style for [ " + homebrew.beerStyle + " ] : ")
    homebrew.beerStyle = readLine()!!
    println(" You updated [ " + homebrew.beerName + " ] for the Beer Name, and [ " + homebrew.beerStyle + " ] for the Beer Style")
}

fun listHomebrews() {
    println("You Chose to List All Homebrews")
}

fun readInt() = readLine()!!.toInt()