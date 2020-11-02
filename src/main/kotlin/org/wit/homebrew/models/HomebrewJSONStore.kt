package org.wit.homebrew.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.homebrew.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "homebrews.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<HomebrewModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class HomebrewJSONStore : HomebrewStore {

    var homebrews = mutableListOf<HomebrewModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<HomebrewModel> {
        return homebrews
    }

    override fun findOne(id: Long) : HomebrewModel? {
        var foundHomebrew: HomebrewModel? = homebrews.find { p -> p.id == id }
        return foundHomebrew
    }

    override fun create(homebrew: HomebrewModel) {
        homebrew.id = generateRandomId()
        homebrews.add(homebrew)
        serialize()
    }

    override fun update(homebrew: HomebrewModel) {
        var foundHomebrew = findOne(homebrew.id!!)
        if (foundHomebrew != null) {
            foundHomebrew.beerName = homebrew.beerName
            foundHomebrew.beerStyle = homebrew.beerStyle
        }
        serialize()
    }

    override fun delete(homebrew: HomebrewModel) {
        homebrews.remove(homebrew)
        serialize()
    }

    internal fun logAll() {
        homebrews.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(homebrews, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        homebrews = Gson().fromJson(jsonString, listType)
    }
}