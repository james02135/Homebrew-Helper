package org.wit.homebrew.controllers

import mu.KotlinLogging
import org.wit.homebrew.models.HomebrewJSONStore
import org.wit.homebrew.models.HomebrewModel
import org.wit.homebrew.views.AddHomebrewScreen
import org.wit.homebrew.views.ListHomebrewScreen
import org.wit.homebrew.views.MenuScreen
import tornadofx.*

class HomebrewUIController : Controller() {

    val homebrews = HomebrewJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Homebrew TornadoFX UI App" }
    }
    fun add(_beerName : String, _beerStyle : String){

        var aHomebrew = HomebrewModel(beerName = _beerName, beerStyle = _beerStyle)
        homebrews.create(aHomebrew)
        logger.info("Homebrew Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListHomebrewScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        homebrews.showAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddHomebrewScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddHomebrewScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListHomebrewScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}