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

    val count = File("src/y2015/day12/day12.txt")
        .readText().countNum()
    println(count)
}
