package y2015.day13

import java.io.File

fun main() {
    val data = readHappinessData()
    val newData = addMe(data)
    newData.forEach(::println)
    getMaxHappiness(newData).let { println(it) }
}

fun addMe(data:Map<String,Map<String,Int>>):Map<String,Map<String,Int>> {
    val newData = mutableMapOf<String, MutableMap<String, Int>>()
    data.forEach { (key, value) ->
        newData[key] = value.toMutableMap()
        newData[key]!!["me"] = 0
        newData.computeIfAbsent("me") {
            mutableMapOf()
        }[key] = 0
    }
    return newData
}