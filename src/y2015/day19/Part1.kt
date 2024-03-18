package y2015.day19

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/18 15:40
 * @Description:
 */
fun main() {
    fun findAllOccurrences(mainString: String, searchString: String): List<Int> {
        val indices = mutableListOf<Int>()
        var currentIndex = mainString.indexOf(searchString)
        while (currentIndex != -1) {
            indices.add(currentIndex)
            currentIndex = mainString.indexOf(searchString, currentIndex + searchString.length)
        }
        return indices
    }

    val map = mutableMapOf<String,MutableList<String>>()
    val resultSet = mutableSetOf<String>()
    var target = ""
    File("src/y2015/day19/input.txt").forEachLine {
        str ->
        val newStr = str.trim()
        if (newStr.isNotBlank()) {
            var split = newStr.split(" => ")
            if (split.size == 2) {
                val (key, value) = split
                map.getOrPut(key) { mutableListOf() }.add(value)
            } else {
                target = newStr
            }
        }
    }
    map.forEach {
        (key,value) ->
        value.forEach {
            rv ->
            var indexList = findAllOccurrences(target, key)
            indexList.forEach {
                val newStr = target.replaceRange(it,it+key.length,rv)
                resultSet.add(newStr)
            }
        }
    }
    println(resultSet.size)
}