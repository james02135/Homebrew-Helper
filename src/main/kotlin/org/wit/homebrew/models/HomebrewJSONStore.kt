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

/*
Generates a random 8 byte integer for the ID
 */
fun generateRandomId(): Long {
    return Random().nextLong()
}

class HomebrewJSONStore : HomebrewStore {
    /*
    List of all the homebrews
     */
    var homebrews = mutableListOf<HomebrewModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    /*
    Returns all of the homebrews from the list
     */
    override fun findAll(): MutableList<HomebrewModel> {
        return homebrews
    }

    /*
    Finds one homebrew from the ID
     */
    override fun findOne(id: Long) : HomebrewModel? {
        var foundHomebrew: HomebrewModel? = homebrews.find { p -> p.id == id }
        return foundHomebrew
    }

    /*
    Adds a new homebrew to the list, as well as a unique ID
     */
    override fun create(homebrew: HomebrewModel) {
        homebrew.id = generateRandomId()
        homebrews.add(homebrew)
        serialize()
    }

    /*
    Updates the beer name and style of the homebrew determined by the ID
     */
    override fun update(homebrew: HomebrewModel) {
        var foundHomebrew = findOne(homebrew.id!!)
        if (foundHomebrew != null) {
            foundHomebrew.beerName = homebrew.beerName
            foundHomebrew.beerStyle = homebrew.beerStyle
        }
        serialize()
    }

    /*
    Deletes the homebrew from the list
     */
    override fun delete(homebrew: HomebrewModel) {
        homebrews.remove(homebrew)
        serialize()
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
    
    private fun serialize() {
        val jsonString = gsonBuilder.toJson(homebrews, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        homebrews = Gson().fromJson(jsonString, listType)
    }
}