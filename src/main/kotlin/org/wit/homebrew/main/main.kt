package org.wit.homebrew.main

import mu.KotlinLogging
import org.wit.homebrew.models.HomebrewModel
import org.wit.homebrew.models.HomebrewMemStore
import org.wit.homebrew.views.HomebrewView

private val logger = KotlinLogging.logger {}
val homebrews = HomebrewMemStore()
val homebrewView = HomebrewView()

fun main(args: Array<String>) {
    logger.info { "Launching Homebrew Helper Console App" }
    println("Homebrew Helper Kotlin App Version 3.0")

    var input: Int

    do {
        input = homebrewView.menu()
        when(input) {
            1 -> addHomebrew()
            2 -> updateHomebrew()
            3 -> homebrewView.listHomebrews(homebrews)
            4 -> homebrewView.findHomebrew(homebrews)
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Homebrew Helper Console App" }
}

fun addHomebrew() {
    var aHomebrew = HomebrewModel()
    if (homebrewView.addHomebrewData(aHomebrew))
        homebrews.create(aHomebrew)
    else
        logger.info("Homebrew not added")
}

fun updateHomebrew() {

    homebrewView.listHomebrews(homebrews)
    var searchId = homebrewView.getId()
    val aHomebrew = search(searchId)

    if(aHomebrew != null) {
        if(homebrewView.updateHomebrewData(aHomebrew)) {
            homebrews.update(aHomebrew)
            homebrewView.findHomebrew(aHomebrew)
            logger.info("Homebrew Updated : [ $aHomebrew ]")
        }
        else
            logger.info("Homebrew Not Updated")
    }
    else
        println("Homebrew Not Updated...")
}

fun searchHomebrew() {
    val aHomebrew = search(homebrewView.getId())!!
    homebrewView.findHomebrew(aHomebrew)
}

fun search(id: Long) : HomebrewModel? {
    var foundHomebrew = homebrews.findOne(id)
    return foundHomebrew
}

fun readInt() = readLine()!!.toInt()

fun readDouble() = readline()!!.toDouble()

