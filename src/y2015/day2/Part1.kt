package y2015.day2

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 10:53
 * @Description:
 */
fun main(args: Array<String>) {
    val count = File("src/y2015/day2/day2.txt").readLines()
        .map {
            str ->
            val (l,w,h) = str.split("x").map(String::toInt)
            (2*l*w + 2*w*h + 2*h*l + minOf(l*w, w*h, h*l))
        }.sum()
    println(count)
}