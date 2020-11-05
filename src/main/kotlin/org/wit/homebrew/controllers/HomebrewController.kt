package org.wit.homebrew.controllers

import mu.KotlinLogging
import org.wit.homebrew.models.HomebrewMemStore
import org.wit.homebrew.models.HomebrewJSONStore
import org.wit.homebrew.models.HomebrewModel
import org.wit.homebrew.views.HomebrewView

class HomebrewController {

    //val homebrews = HomebrewMemStore()
    val homebrews = HomebrewJSONStore()
    val homebrewView = HomebrewView()
    val logger = KotlinLogging.logger {}

    init {
        //logger.info { "Launching Homebrew Console App" }
        println("Homebrew Kotlin App Version 4.0")
        println()
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
                -1 -> println()
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        println("Shutting down the Homebrew Kotlin App...")
    }

    fun menu() :Int { return homebrewView.menu() }

    /*
    Takes the data entered by the user in HomebrewView.kt in the addHomebrew funtion
    and adds it to the list of homebrews
     */
    
    fun add(){
        var aHomebrew = HomebrewModel()
        if (homebrewView.addHomebrewData(aHomebrew)) {
            homebrews.create(aHomebrew)
            println("Homebrew has been added")
        }
        else {
            println("Homebrew Not Added")
        }
    }

    /*
    Lists all the homebrews contained in the JSON file
     */

    fun list() {
        homebrewView.listHomebrews(homebrews)
    }

    /*
    Updates the name and style of a homebrew after the
    user has entered the ID of the homebrew
     */

    fun update() {
        homebrewView.listHomebrews(homebrews)
        var searchId = homebrewView.getId()
        val aHomebrew = search(searchId)
        if(aHomebrew != null) {
            if(homebrewView.updateHomebrewData(aHomebrew)) {
                homebrews.update(aHomebrew)
                homebrewView.showHomebrew(aHomebrew)
            }
        }
    }

    /*
    Deletes the homebrew from the list once the user
    has entered the ID of the homebrew
     */

    fun delete() {
        homebrewView.listHomebrews(homebrews)
        var searchId = homebrewView.getId()
        var aHomebrew = search(searchId)
        if (aHomebrew != null) {
            homebrews.delete(aHomebrew)
            println()
            println("Homebrew has been deleted")
        }
        else
            println("Homebrew Not Deleted")
    }

    /*
    Helper function to search the list
     */

    fun search() {
        homebrewView.listHomebrews(homebrews)
        val aHomebrew = search(homebrewView.getId())!!
        homebrewView.showHomebrew(aHomebrew)
    }

    /*
    searches the list for the entered ID
     */

    fun search(id: Long) : HomebrewModel? {
        var foundHomebrew = homebrews.findOne(id)
        return foundHomebrew
    }
}