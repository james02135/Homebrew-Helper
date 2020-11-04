package org.wit.homebrew.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.homebrew.controllers.HomebrewUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddHomebrewScreen : View("Add Homebrew") {
    val model = ViewModel()
    val _beerName = model.bind { SimpleStringProperty() }
    val _beerStyle = model.bind { SimpleStringProperty() }
    val homebrewUIController: HomebrewUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Beer Name") {
                textfield(_beerName).required()
            }
            field("Beer Style") {
                textarea(_beerStyle).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        homebrewUIController.add(_beerName.toString(),_beerStyle.toString())

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        homebrewUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _beerName.value = ""
        _beerStyle.value = ""
        model.clearDecorators()
    }
}
