package org.wit.homebrew.views

import org.wit.homebrew.main.*
import org.wit.homebrew.models.HomebrewStore
import org.wit.homebrew.models.HomebrewMemStore
import org.wit.homebrew.models.HomebrewJSONStore
import org.wit.homebrew.models.HomebrewModel

class HomebrewView {
    fun menu() : Int {

        var option : Int
        var input: String? = null

        println("MAIN MENU")
        println(" 1. Add a Homebrew")
        println(" 2. Update a Homebrew")
        println(" 3. List all Homebrews")
        println(" 4. Find a Homebrew")
        println(" 5. Delete a Homebrew")
        println("-1. Exit")
        println()
        print("Enter your choice : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listHomebrews(homebrews : HomebrewJSONStore) {
        if(homebrews != null) {
            println("You chose to list all homebrews")
            println()
            homebrews.showAll()
            println()
        }
    }

    fun showHomebrew(homebrew : HomebrewModel) {
        if(homebrew != null) {
            println("Homebrew Details")
            println("Beer Name : [ " + homebrew.beerName + " ] ")
            println("Beer ID : [ " + homebrew.id + " ] ")
            println("Beer Style : [ " + homebrew.beerStyle + " ] ")
            println("ABV : [ " + homebrew.ABV + " ] ")
            println("Primary Malt : [ " + homebrew.malt1 + " ]")
            if (homebrew.malts != null && !homebrew.malts.isEmpty()) {
                println("Additional Malt(s) : [ " + homebrew.malts + " ]")
            }
            println("Boil Length : [ " + homebrew.boilLength + " ]")
            println("Primary Hop : [ " + homebrew.hop1 + " ]")
            println(homebrew.hop1 + " was added " + homebrew.hopTime1 + " minutes remaining in the boil")
            if (homebrew.hops != null && !homebrew.hops.isEmpty()) {
                println("Additional Hop(s) : [ " + homebrew.hops + " ]")
            }
            if (homebrew.hopTimes != null && !homebrew.hopTimes.isEmpty()) {
                    println("Hops were added to the boil at " + homebrew.hopTimes + " minutes remaining in the boil respectively")
            }
            println("Yeast : [ " + homebrew.yeast + " ] ")
            println("Original Gravity(OG) : " + homebrew.origGrav)
            println("Final Gravity(FG) : " + homebrew.finalGrav)
            if (homebrew.dryHop == "y") {
                println("Dry Hopped with " + homebrew.dryHopType + " for " + homebrew.dryHopLength + " days")
            }
        }
    }

    fun addHomebrewData(homebrew : HomebrewModel) : Boolean {
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
        homebrew.boilLength = readInt()
        println()
        print("Enter the first type of hop used : ")
        homebrew.hop1 = readLine()!!
        println()
        print("Enter how many minutes left in the boil " + homebrew.hop1 + " was added : ")
        homebrew.hopTime1 = readInt()
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
                    print("Enter how many minutes left in the boil the hop was added : ")
                    homebrew.hopTimes.add(readInt())
                    println()
                }
            }
        }
        println()
        print("Enter the yeast name : ")
        homebrew.yeast = readLine()!!
        println()
        print("Enter the measured Original Gravity : ")
        homebrew.origGrav = readDouble()
        println()
        print("Enter the measured Final Gravity : ")
        homebrew.finalGrav = readDouble()
        println()
        print("Enter the Final ABV : ")
        homebrew.ABV = readDouble()
        println()
        print("Did you dry hop? (y/n) : ")
        homebrew.dryHop = readLine()!!
        if (homebrew.dryHop != "n") {
            println()
            print("Enter the hop type used to dry hop : ")
            homebrew.dryHopType = readLine()!!
            println()
            print("How long did you dry hop : ")
            homebrew.dryHopLength = readInt()
            println()
        }
        return homebrew.beerName.isNotEmpty() && homebrew.beerStyle.isNotEmpty()
    }

    fun updateHomebrewData(homebrew :HomebrewModel) : Boolean{
        println("You Chose to Update a Homebrew")
        println()
        var tempName : String?
        var tempStyle : String?

        if(homebrew != null) {
            print("Enter a new Beer Name for [ " + homebrew.beerName + " ] : ")
            tempName = readLine()!!
            print("Enter a new Beer Style for [ " + homebrew.beerStyle + " ] : ")
            tempStyle = readLine()!!
            if (!tempName.isNullOrEmpty() && !tempStyle.isNullOrEmpty()) {
                homebrew.beerName = tempName
                homebrew.beerStyle = tempStyle
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String?
        var searchId : Long
        print("Enter the ID of the Homebrew to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}

fun readInt() = readLine()!!.toInt()

fun readDouble() = readLine()!!.toDouble()