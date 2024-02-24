package y2015.day13

import java.io.File

fun main() {
    val data = readHappinessData()
    getMaxHappiness(data).let { println(it) }
}

fun getMaxHappiness(data:Map<String,Map<String,Int>>):Int {
    return data.keys
        .toList()
        .permutations()
        .maxOf { it.calculateHappiness(data) }
}

fun readHappinessData():Map<String,Map<String,Int>> {
    val data = mutableMapOf<String, MutableMap<String, Int>>()
    File("src/y2015/day13/day13.txt")
        .forEachLine { line ->
            val (n1, f, numStr, n2) = Regex("""(\w+) would (\w+) (\d+) happiness units by sitting next to (\w+).""").find(
                line
            )?.destructured
                ?: throw IllegalArgumentException("")
            data.computeIfAbsent(n1) {
                mutableMapOf()
            }[n2] = if (f == "gain") numStr.toInt() else -numStr.toInt()
        }
    return data
}

fun List<String>.calculateHappiness(happinessData:Map<String,Map<String,Int>>):Int {
    return (this + this.first())
        .zipWithNext().sumOf {
            happinessData[it.first]!![it.second]!! + happinessData[it.second]!![it.first]!!
        }
}

fun <T> List<T>.permutations(): List<List<T>> {
    val list = this
    if (list.size <= 1) return listOf(list)
    return list.indices.flatMap { index ->
        list.toMutableList().apply { removeAt(index) }.permutations().map {
            listOf(list[index]) + it
        }
    }
}