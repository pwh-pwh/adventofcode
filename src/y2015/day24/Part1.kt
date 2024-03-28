package y2015.day24

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/28 17:29
 * @Description:
 */
fun main() {
    val numList = File("src/y2015/day24/input.txt")
        .readLines()
        .map {
            it.toInt()
        }

}