package y2015.day9

import java.io.File

fun main() {
    val routes = readRoutesFromFile("src/y2015/day9/day9.txt")
    val bestRoute = findShortestRoute(routes)
    println(bestRoute.distance)
}

fun readRoutesFromFile(fileName: String): Map<String, Map<String, Int>> {
    val routes = mutableMapOf<String, MutableMap<String, Int>>()
    File(fileName).forEachLine { line ->
        val (src, dst, distStr) = Regex("""(\w+) to (\w+) = (\d+)""").find(line)?.destructured
            ?: throw IllegalArgumentException("Invalid line format: $line")
        val distance = distStr.toInt()

        routes.computeIfAbsent(src) { mutableMapOf() }[dst] = distance
        routes.computeIfAbsent(dst) { mutableMapOf() }[src] = distance
    }
    return routes
}

fun findShortestRoute(routes: Map<String, Map<String, Int>>): Route {
    val cities = routes.keys
    val r = cities.asSequence()
        .permutations()
        .map { it to it.calculateTotalDistance(routes) }
        .minByOrNull { it.second }
        ?: throw IllegalStateException("No routes found")
    return Route(r.first, r.second)
}

fun List<String>.calculateTotalDistance(routes: Map<String, Map<String, Int>>): Int {
    return this.zipWithNext().sumOf { (from, to) ->
        routes[from]?.get(to)
            ?: throw IllegalStateException("No route from $from to $to")
    }
}

fun <T> Sequence<T>.permutations(): Sequence<List<T>> {
    val list = this.toList()
    if (list.size <= 1) return sequenceOf(list)
    return list.indices.asSequence().flatMap { index ->
        list.toMutableList().apply { removeAt(index) }.asSequence().permutations().map {
            listOf(list[index]) + it
        }
    }
}

data class Route(val ordering: List<String>, val distance: Int)