package org.wit.homebrew.controllers

import mu.KotlinLogging
import org.wit.homebrew.models.HomebrewMemStore
import org.wit.homebrew.models.HomebrewModel
import org.wit.homebrew.views.HomebrewView

class HomebrewController {

    val homebrews = HomebrewMemStore()
    val homebrewView = HomebrewView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Homebrew Console App" }
        println("Homebrew Kotlin App Version 1.0")
    }

    fun menu() :Int { return homebrewView.menu() }

    fun add(){
        var aHomebrew = HomebrewModel()

        if (homebrewView.addHomebrewData(aHomebrew))
            homebrews.create(aHomebrew)
        else
            logger.info("Homebrew Not Added")
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
                homebrewView.findHomebrew(aHomebrew)
                logger.info("Homebrew Updated : [ $aHomebrew ]")
            }
            else
                logger.info("Homebrew Not Updated")
        }
        else
            println("Homebrew Not Updated...")
    }

    fun search() {
        val aHomebrew = search(homebrewView.getId())!!
        homebrewView.findHomebrew(aHomebrew)
    }


    fun search(id: Long) : HomebrewModel? {
        var foundHomebrew = homebrews.findOne(id)
        return foundHomebrew
    }
}