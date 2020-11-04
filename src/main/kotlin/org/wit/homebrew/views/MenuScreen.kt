package org.wit.homebrew.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.homebrew.controllers.HomebrewUIController
import tornadofx.*

class MenuScreen : View("Homebrew Main Menu") {

    val homebrewUIController: HomebrewUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Homebrew") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        homebrewUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Homebrews") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        homebrewUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}