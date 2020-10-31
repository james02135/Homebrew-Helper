package org.wit.homebrew.views

import org.wit.homebrew.main.*
import org.wit.homebrew.models.HomebrewStore
import org.wit.homebrew.models.HomebrewMemStore
import org.wit.homebrew.models.HomebrewModel

class HomebrewView {
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

    fun listHomebrews(homebrews : HomebrewMemStore) {
        println("You Chose to List All Homebrews")
        println()
        homebrews.logAll()
        println()
    }

    fun findHomebrew(homebrews : HomebrewModel) {
        if(homebrew != null)
            println("Homebrew Details [ $aHomebrew ]")
        else
            println("Homebrew not found...")

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
        homebrew.origGrav = readDouble()!!
        println()
        print("Enter the measured Final Gravity : ")
        homebrew.finalGrav = readDouble()!!
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
        return homebrew.beerName.isNotEmpty() && homebrew.beerStyle.isNotEmpty()
    }

    fun updateHomebrewData(homebrew :HomebrewModel) : Boolean{
        println("You Chose to Update a Homebrew")
        println()
        var tempName : String?
        var tempStyle : String?

        if(aHomebrew != null) {
            print("Enter a new Beer Name for [ " + homebrew.beerName + " ] : ")
            tempName = readLine()!!
            print("Enter a new Beer Style for [ " + homebrew.beerStyle + " ] : ")
            tempStyle = readLine()!!
            if (!tempName.isNullOrEmpty() && !tempStyle.isNullOrEmpty()) {
                aHomebrew.beerName = tempName
                aHomebrew.beerStyle = tempStyle
                return true
            }

        }
        return false
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
}