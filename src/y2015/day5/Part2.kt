package y2015.day5

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 14:28
 * @Description:
 */
fun main() {
    val count = File("src/y2015/day5/day5.txt").readLines().count { it.isNice() }
    println(count)
}

private fun String.isNice(): Boolean {
    val hc = windowedSequence(2).withIndex()
        .any {
            if (it.index+2 >= this.length-1) return@any false
            this.substring(it.index+2).contains(it.value)
        }
    val hasRepeatOL = windowedSequence(3).any {
        it[0] == it[2]
    }
    return hasRepeatOL && hc
}