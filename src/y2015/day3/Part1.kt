package y2015.day3

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 11:30
 * @Description:
 */
fun main() {
    var location = 0 to 0
    val visited = mutableSetOf<Pair<Int,Int>>()
    visited.add(location)
    File("src/y2015/day3/day3.txt").readText()
        .forEach {
            c ->
            when (c) {
                '^' -> {
                    location = location.copy(second = location.second + 1)
                    visited.add(location)
                }
                'v' -> {
                    location = location.copy(second = location.second - 1)
                    visited.add(location)
                }
                '<' -> {
                    location = location.copy(first = location.first - 1)
                    visited.add(location)
                }
                '>' -> {
                    location = location.copy(first = location.first + 1)
                    visited.add(location)
                }
            }
        }
    println(visited.size)
}