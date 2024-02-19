package y2015.day10

/**
 * @Auther: coderpwh
 * @Date: 2024/2/19 10:58
 * @Description:
 */
fun main() {
    var input = "1113222113"
    repeat(40) {
        doLookSay(input).also { input = it }
    }
    println(input.length)
}

fun doLookSay(input: String): String {
    val sb = StringBuilder()
    var count = 1

    for (i in 1 until input.length) {
        if (input[i] == input[i - 1]) {
            count++
        } else {
            sb.append(count).append(input[i - 1])
            count = 1
        }
    }

    // Append the last sequence
    sb.append(count).append(input.last())

    return sb.toString()
}