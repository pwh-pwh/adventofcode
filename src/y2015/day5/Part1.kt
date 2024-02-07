package y2015.day5

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 14:28
 * @Description:
 */
fun main() {
    var count = 0
    File("src/y2015/day5/day5.txt").forEachLine {
        if (it.isNice()) {
            count++
        }
    }
    println(count)
}

private fun String.isNice(): Boolean {
    return this.filter {
        it == 'a' || it == 'e' || it == 'i' || it == 'o' || it == 'u'
    }.count() > 2 && !this.checkContains() && this.charTwice()
}

private fun String.checkContains(): Boolean {
    return this.contains("ab") || this.contains("cd") || this.contains("pq") || this.contains("xy")
}

private fun String.charTwice(): Boolean {
    return this.windowed(2).any { it[0] == it[1] }
}