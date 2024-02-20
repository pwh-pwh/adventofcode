package y2015.day12

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/20 15:48
 * @Description:
 */
fun main() {
    fun String.countNum():Int {
        return Regex("-?\\d+").findAll(this).sumOf {
            it.value.toInt()
        }
    }

    fun findBoundary(input: String, start: Int, goal: Int): Int {
        var value = 0
        var index = start
        while (value != goal) {
            index -= goal
            if (input[index] == '}') {
                value -= 1
            }
            if (input[index] == '{') {
                value += 1
            }
        }
        return index
    }

    fun rpRed2Zero(input: String): String {
        var newInput = input
        while (true) {
            val pos = newInput.indexOf(":\"red\"")
            if (pos < 0) {
                break
            }
            val start = findBoundary(newInput, pos, 1)
            val end = findBoundary(newInput, pos, -1)
            newInput = newInput.substring(0, start) + "0" + newInput.substring(end + 1)
        }
        return newInput
    }

    val readText = File("src/y2015/day12/day12.txt")
        .readText()
    println(rpRed2Zero(readText).countNum())
}
