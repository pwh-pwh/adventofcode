package y2015.day14

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/5 16:05
 * @Description:
 */
fun main() {
    val max = File("src/y2015/day14/day14.txt").readLines()
        .maxOf {
            1
        }
    println("max is $max")
}