package org.wit.homebrew.controllers

import mu.KotlinLogging
import org.wit.homebrew.models.HomebrewJSONStore
import org.wit.homebrew.models.HomebrewModel
import org.wit.homebrew.views.HomebrewView

class HomebrewController {

    val homebrews = HomebrewJSONStore()
    val homebrewView = HomebrewView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Homebrew Console App" }
        println("Homebrew Kotlin App Version 4.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when(input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Homebrew Helper Console App" }
    }

    fun menu() :Int { return homebrewView.menu() }

    fun add(){
        var aHomebrew = HomebrewModel()
        if (homebrewView.addHomebrewData(aHomebrew)) {
            homebrews.create(aHomebrew)
            logger.info("Homebrew Added")
        }
        else {
            logger.info("Homebrew Not Added")
        }
    }

    fun list() {
        homebrewView.listHomebrews(homebrews)
    }

    fun update() {
        homebrewView.listHomebrews(homebrews)
        var searchId = homebrewView.getId()
        val aHomebrew = search(searchId)
        if(aHomebrew != null) {
            if(homebrewView.updateHomebrewData(aHomebrew)) {
                homebrews.update(aHomebrew)
                homebrewView.showHomebrew(aHomebrew)
                logger.info("Homebrew Updated : [ $aHomebrew ]")
            }
            else
                logger.info("Homebrew Not Updated")
        }
        else
            println("Homebrew Not Updated")
    }

    fun delete() {
        homebrewView.listHomebrews(homebrews)
        var searchId = homebrewView.getId()
        var aHomebrew = search(searchId)
        if (aHomebrew != null) {
            homebrews.delete(aHomebrew)
            println("Homebrew Deleted")
            homebrewView.listHomebrews(homebrews)
        }
        else
            println("Homebrew Not Deleted")
    }

    fun search() {
        homebrewView.listHomebrews(homebrews)
        val aHomebrew = search(homebrewView.getId())!!
        homebrewView.showHomebrew(aHomebrew)
    }

    fun search(id: Long) : HomebrewModel? {
        var foundHomebrew = homebrews.findOne(id)
        return foundHomebrew
    }
}