package org.wit.homebrew.models

import mu.KotlinLogging

import org.wit.homebrew.main.main
import org.wit.homebrew.models.HomebrewModel
import org.wit.homebrew.models.HomebrewStore

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class HomebrewMemStore : HomebrewStore {

    /*
    New ArrayList of homebrews
     */
    val homebrews = ArrayList<HomebrewModel>()

    /*
    Finds all the homebrews from the list
     */
    override fun findAll(): List<HomebrewModel> {
        return homebrews
    }

    /*
    Finds one homebrew from the list from the ID
     */
    override fun findOne(id: Long) : HomebrewModel? {
        var foundHomebrew: HomebrewModel? = homebrews.find { p -> p.id == id }
        return foundHomebrew
    }

    /*
    Adds a new homebrew to the list
     */
    override fun create(homebrew: HomebrewModel) {
        homebrew.id = getId()
        homebrews.add(homebrew)
        showAll()
    }

    /*
    Updates the beer name and style after the ID is entered
     */
    override fun update(homebrew: HomebrewModel) {
        var foundHomebrew = findOne(homebrew.id!!)
        if (foundHomebrew != null) {
            foundHomebrew.beerName = homebrew.beerName
            foundHomebrew.beerStyle = homebrew.beerStyle
        }
    }

    /*
    Deletes a homebrew from the list
     */
    override fun delete(homebrew: HomebrewModel) {
        homebrews.remove(homebrew)
    }

    /*
   Shows all the homebrews in the list by the ID and name
    */
    public fun showAll() {
        val homebrewsIterator = homebrews.iterator()
        while (homebrewsIterator.hasNext() == true) {
            var aHomebrew = homebrewsIterator.next()
            println("Beer ID : " + aHomebrew.id + " , Beer Name : " + aHomebrew.beerName)
        }
    }
}