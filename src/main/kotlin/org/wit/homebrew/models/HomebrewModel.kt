package org.wit.homebrew.models
/*
Framework for a new homebrew
 */
 data class HomebrewModel(
     var id: Long? = 0,
     var beerName: String = "",
     var beerStyle: String = "",
     var anotherMalt : Boolean = false,
     var nextMalt : Int = 0,
     var maltChoice : String = "",
     var malt1 : String = "",
     val malts : MutableList<String> = mutableListOf(),
     var boilLength : Int = 0,
     var anotherHop : Boolean = false,
     var nextHop : Int = 0,
     var hopChoice : String = "",
     var hop1 : String = "",
     var hopTime1 : Int = 0,
     val hops : MutableList<String> = mutableListOf(),
     val hopTimes : MutableList<Int> = mutableListOf(),
     var yeast : String = "",
     var origGrav : Double = 0.0,
     var finalGrav : Double = 0.0,
     var ABV : Double = 0.0,
     var dryHop : String = "",
     var dryHopType : String = "",
     var dryHopLength : Int = 0,
 )