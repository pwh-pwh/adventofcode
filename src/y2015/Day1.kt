package y2015

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 09:50
 * @Description:
 */
fun main(args: Array<String>) {
    val floor = File("src/y2015/day1.txt").readText()
        .sumOf {
            when (it) {
                '(' -> 1
                ')' -> -1
                else -> 0
            } as Int
        }
    println(floor)
}