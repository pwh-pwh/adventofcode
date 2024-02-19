package y2015.day9

import java.io.File

fun main() {
    val routes = readRoutesFromFile("src/y2015/day9/day9.txt")
    val bestRoute = findLongestRoute(routes)
    println(bestRoute.distance)
}



fun findLongestRoute(routes: Map<String, Map<String, Int>>): Route {
    val cities = routes.keys
    val r = cities.asSequence()
        .permutations()
        .map { it to it.calculateTotalDistance(routes) }
        .maxByOrNull { it.second }
        ?: throw IllegalStateException("No routes found")
    return Route(r.first, r.second)
}

