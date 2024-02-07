package y2015.day1

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 10:18
 * @Description:
 */
fun main() {
    var count = 0;
    var position = File("src/y2015/day1/day1.txt").readText()
        .indexOfFirst {
            count += if (it == '(') 1 else -1
            count == -1
        } + 1
    println(position)
}