package y2015.day3

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 11:30
 * @Description:
 */
fun main() {
    val visited = mutableSetOf(0 to 0)  // Initialize with the starting point
    val directions = File("src/y2015/day3/day3.txt").readText().mapNotNull {
        when (it) {
            '^' -> 0 to 1
            'v' -> 0 to -1
            '<' -> -1 to 0
            '>' -> 1 to 0
            else -> null
        }
    }

    // Separate the directions based on even and odd indices
    val (evenDirections, oddDirections) = directions.withIndex().partition { it.index % 2 == 0 }

    // Process the even-indexed directions
    var evenLocation = 0 to 0
    evenDirections.forEach {
        evenLocation += it.value
        visited.add(evenLocation)
    }

    // Process the odd-indexed directions
    var oddLocation = 0 to 0
    oddDirections.forEach {
        oddLocation += it.value
        visited.add(oddLocation)
    }

    println(visited.size)  // Print the number of unique locations visited
}

// Define a '+' operator function for adding two pairs
operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first + other.first) to (this.second + other.second)
}