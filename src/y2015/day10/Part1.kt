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
    var preChar = input[0]
    var count = 1
    var sb = StringBuilder()
    for (i in 1 until input.length) {
        if (input[i] == preChar) {
            count+=1
            preChar = input[i]
            if (i == input.length - 1) {
                sb.append(count).append(preChar)
            }
        } else {
            sb.append(count).append(preChar)
            count = 1
            preChar = input[i]
            if (i == input.length - 1) {
                sb.append(count).append(input[i])
            }
        }
    }
    return sb.toString()
}