package org.wit.homebrew.models

import org.wit.homebrew.main.main
import org.wit.homebrew.models.HomebrewModel
import org.wit.homebrew.models.HomebrewMemStore

interface HomebrewStore {
    fun findAll(): List<HomebrewModel>
    fun findOne(id: Long): HomebrewModel?
    fun create(homebrew: HomebrewModel)
    fun update(homebrew: HomebrewModel)
    fun delete(homebrew: HomebrewModel)
}