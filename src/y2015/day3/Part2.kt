package y2015.day3

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 11:30
 * @Description:
 */
fun main() {
    val visited = mutableSetOf<Pair<Int, Int>>().apply { add(0 to 0) }
    val directions = File("src/y2015/day3/day3.txt").readText().map {
        when (it) {
            '^' -> 0 to 1
            'v' -> 0 to -1
            '<' -> -1 to 0
            '>' -> 1 to 0
            else -> null
        }
    }.filterNotNull()
    val (even, odd) = directions.withIndex().partition {
        it.index %2 == 0
    }
    even.map {
        it.value
    }.fold(0 to 0) { loc, move ->
        ((loc.first + move.first) to (loc.second + move.second))
            .also { visited.add(it) }
    }
    odd.map {
        it.value
    }.fold(0 to 0) { loc, move ->
        ((loc.first + move.first) to (loc.second + move.second))
            .also { visited.add(it) }
    }
    println(visited.size)
}