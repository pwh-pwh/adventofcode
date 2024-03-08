package y2015.day17

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/8 16:47
 * @Description:
 */
fun main() {
    val list = File("src/y2015/day17/input.txt")
        .readLines()
        .map {
            it.toInt()
        }.sorted()
    println(list)
}