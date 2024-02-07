package y2015.day1

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 10:18
 * @Description:
 */
fun main() {
    var flag = false
    var position = 0;
    var count = 0;
        File("src/y2015/day1/day1.txt").readText()
            .forEachIndexed { index, c ->
                if (c == '(') {
                    count++
                } else if (c == ')') {
                    count--
                }
                if (count == -1 && !flag) {
                    position = index + 1
                    flag = true
                }
            }

    println(position)
}