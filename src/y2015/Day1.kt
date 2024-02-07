package y2015

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 09:50
 * @Description:
 */
fun main(args: Array<String>) {
    var floor = 0;
    var count = 0;
    File("src/y2015/day1.txt").readText()
        .toCharArray().forEach {
            c ->
            count++;
            when (c) {
                '(' -> {
                    floor++;
                }
                ')' -> {
                    floor--;
                }
            }
        }
    println(floor)
    println(count)
}