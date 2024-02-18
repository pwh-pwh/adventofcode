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
            var count = it.strMemoryLen()
            it.length - count + 2
        }
    println(result)
}

private fun String.strMemoryLen():Int {
    var count = 0
    val iterator = this.iterator()
    var state = State.START
    while (iterator.hasNext()) {
        val c = iterator.next()
        state = when (state) {
            State.START -> {
                if (c == '\\') {
                    State.FIRSTL
                } else {
                    count++
                    State.START
                }
            }
            State.FIRSTL -> {
                if (c == 'x') {
                    State.X
                } else {
                    count++
                    State.START
                }
            }
            State.X -> {
                iterator.next()
                count++
                State.START
            }
        }
    }
    return count
}

private enum class State {
    START,FIRSTL,X
}