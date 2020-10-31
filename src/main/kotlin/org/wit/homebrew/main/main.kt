package org.wit.homebrew.main

import mu.KotlinLogging
import org.wit.homebrew.models.HomebrewModel

private val logger = KotlinLogging.logger {}
val homebrews = ArrayList<HomebrewModel>()

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
            4 -> findHomebrew()
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
    println(" 4. Find a Homebrew")
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
    var aHomebrew = HomebrewModel()
    aHomebrew.anotherMalt = true
    aHomebrew.anotherHop = true
    aHomebrew.nextMalt = 0
    aHomebrew.nextHop = 0

    println("You Chose to Add a New Homebrew")
    println()
    print("Enter the beer name : ")
    aHomebrew.beerName = readLine()!!
    println()
    print("Enter the beer style : ")
    aHomebrew.beerStyle = readLine()!!
    println()
    print("Enter the first malt used : ")
    aHomebrew.malt1 = readLine()!!
    println()
    while (aHomebrew.anotherMalt != false) {
        while (aHomebrew.nextMalt < 3) {
            print("Would you like to enter another malt? (y/n) : ")
            aHomebrew.maltChoice = readLine()!!
            if (aHomebrew.maltChoice.equals("n")) {
                aHomebrew.anotherMalt = false
                break
            } else {
                println()
                print("Enter the next malt used : ")
                aHomebrew.malts.add(readLine()!!)
                println()
                aHomebrew.nextMalt++
            }
        }
    }
    println()
    print("Enter the boil length in minutes : ")
    println()
    aHomebrew.boilLength = readInt()!!
    println()
    print("Enter the first type of hop used : ")
    aHomebrew.hop1 = readLine()!!
    println()
    print("Enter when [ " + aHomebrew.hop1 + " ] was added to the boil : ")
    aHomebrew.hopTime1 = readInt()!!
    println()
    while (aHomebrew.anotherHop != false) {
        while (aHomebrew.nextHop < 3) {
            print("Would you like to enter another hop? (y/n) : ")
            aHomebrew.hopChoice = readLine()!!
            if (aHomebrew.hopChoice.equals("n")) {
                aHomebrew.anotherHop = false
                break
            } else {
                println()
                print("Enter the next hop used : ")
                aHomebrew.hops.add(readLine()!!)
                println()
                aHomebrew.nextHop++
                print("Enter when the hop was added to the boil : ")
                aHomebrew.hopTimes.add(readInt()!!)
                println()
            }
        }
    }
    println()
    print("Enter the yeast name : ")
    aHomebrew.yeast = readLine()!!
    println()
    print("Enter the measured Original Gravity : ")
    aHomebrew.origGrav = readInt()!!
    println()
    print("Enter the measured Final Gravity : ")
    aHomebrew.finalGrav = readInt()!!
    println()
    print("Did you dry hop? (y/n) : ")
    aHomebrew.dryHop = readLine()!!
        if (aHomebrew.dryHop != "n") {
            println()
            print("Enter the hop type used to dry hop : ")
            aHomebrew.dryHopType = readLine()!!
            println()
            print("How long did you dry hop : ")
            aHomebrew.dryHopLength = readInt()!!
            println()
        }
    if (aHomebrew.beerName.isNotEmpty() && aHomebrew.beerStyle.isNotEmpty()) {
        homebrews.add(homebrew.copy())
        logger.info("Homebrew Added : [ $homebrew ]")
        aHomebrew.id++
    }
    else
        logger.info("Homebrew Not Added")

}

fun updateHomebrew() {
    println("You Chose to Update a Homebrew")
    println()
    listHomebrews()
    var searchId = getId()
    val aHomebrew = search(searchId)
    if(aHomebrew != null) {
        print("Enter a new Beer Name for [ " + homebrew.beerName + " ] : ")
        homebrew.beerName = readLine()!!
        print("Enter a new Beer Style for [ " + homebrew.beerStyle + " ] : ")
        homebrew.beerStyle = readLine()!!
        println(" You updated [ " + homebrew.beerName + " ] for the Beer Name, and [ " + homebrew.beerStyle + " ] for the Beer Style")
    } else {
        println("Homebrew not updated")
    }
}

fun listHomebrews() {
    println("You Chose to List All Homebrews")
    println()
    homebrews.forEach { logger.info("${it}") }
    println()
}

fun findHomebrew() {
    var searchId = getId()
    val aHomebrew = search(searchId)
    if(aHomebrew != null)
        println("Homebrew Details [ $aHomebrew ]")
    else
        println("Homebrew not found...")

}

fun getId() : Long {
    var strId : String?
    var searchId : Long
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : HomebrewModel? {
    var foundHomebrew: HomebrewModel? = homebrews.find { p -> p.id == id }
    return foundHomebrew
}

fun readInt() = readLine()!!.toInt()

