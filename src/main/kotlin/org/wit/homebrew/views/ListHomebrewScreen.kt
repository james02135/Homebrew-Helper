package org.wit.homebrew.views

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.homebrew.controllers.HomebrewUIController
import org.wit.homebrew.models.HomebrewModel
import tornadofx.*

class ListHomebrewScreen : View("List Homebrews") {

    val homebrewUIController: HomebrewUIController by inject()
    val tableContent = homebrewUIController.homebrews.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", HomebrewModel::id)
            readonlyColumn("BEER NAME", HomebrewModel::beerName)
            readonlyColumn("BEER STYLE", HomebrewModel::beerStyle)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    homebrewUIController.closeList()
                }
            }
        }
    }

}