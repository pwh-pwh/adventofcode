package y2015.day2

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 10:53
 * @Description:
 */
fun main(args: Array<String>) {
    val count = File("src/y2015/day2/day2.txt").readLines()
        .sumOf {
            val (l, w, h) = it.split("x").map(String::toInt)
            val sideAreas = listOf(l*w, w*h, h*l)
            2 * sideAreas.sum() + sideAreas.min()
        }
    println(count)
}