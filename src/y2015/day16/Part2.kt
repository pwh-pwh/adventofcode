package y2015.day16

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/8 15:59
 * @Description:
 */
fun main() {
    val validMap = mapOf(
        "children" to 3, "cats" to 7, "samoyeds" to 2,
        "pomeranians" to 3, "akitas" to 0, "vizslas" to 0,
        "goldfish" to 5, "trees" to 3, "cars" to 2, "perfumes" to 1
    )
    var rl = File("src/y2015/day16/input.txt")
        .readLines().map { input ->
            val regex = """(\w+): (\d+)""".toRegex()
            val matches = regex.findAll(input)
            var vm = mutableMapOf<String, Int>()
            for (match in matches) {
                val (key, value) = match.destructured
                vm[key] = value.toInt()
            }
            vm
        }.withIndex()
        .filter { (_, vm) ->
            vm.all { (key, value) ->
                when (key) {
                    "cats" -> value > validMap[key]!!
                    "trees" -> value > validMap[key]!!
                    "pomeranians" -> value < validMap[key]!!
                    "goldfish" -> value < validMap[key]!!
                    else -> validMap[key] == value
                }
            }
        }.toList()
    println(rl)
}