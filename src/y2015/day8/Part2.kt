package y2015.day8

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/18 10:43
 * @Description:
 */
fun main() {

    val result = File("src/y2015/day8/day8.txt").readLines()
        .sumOf {
            it.count {
                it == '\\' || it == '"'
            } + 2
        }
    println(result)
}