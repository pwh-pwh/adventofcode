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
            val (s1,s2) = listOf(l,w,h).sorted().take(2)
            (2*s1 + 2*s2 + l*w*h)
        }
    println(count)
}