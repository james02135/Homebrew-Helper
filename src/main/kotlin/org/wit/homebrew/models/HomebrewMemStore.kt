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

    val homebrews = ArrayList<HomebrewModel>()

    override fun findAll(): List<HomebrewModel> {
        return homebrews
    }

    override fun findOne(id: Long) : HomebrewModel? {
        var foundHomebrew: HomebrewModel? = homebrews.find { p -> p.id == id }
        return foundHomebrew
    }

    override fun create(homebrew: HomebrewModel) {
        homebrew.id = getId()
        homebrews.add(homebrew)
        logAll()
    }

    override fun update(homebrew: HomebrewModel) {
        var foundHomebrew = findOne(homebrew.id!!)
        if (foundHomebrew != null) {
            foundHomebrew.beerName = homebrew.beerName
            foundHomebrew.beerStyle = homebrew.beerStyle
        }
    }

    override fun delete(homebrew: HomebrewModel) {
        homebrews.remove(homebrew)
    }

    internal fun logAll() {
        homebrews.forEach { logger.info("${it}") }
    }
}