package y2015.day9

import java.io.File

fun main() {
    val m = File("src/y2015/day9/day9.txt")
        .readLines()
        .flatMap {
            val (from, to, distance) = it.split(" to ", " = ")
            listOf(from to Dist(to, distance.toInt()),to to Dist(from, distance.toInt()))
        }.toMap()
    println(m.size)
    println(m)
}

private data class Dist( val to: String, val distance: Int)