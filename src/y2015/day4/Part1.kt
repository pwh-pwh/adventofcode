package y2015.day4

import java.security.MessageDigest

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 14:09
 * @Description:
 */
fun main() {
    val input = "yzbqklnj"
    var num = 1
    while (true) {
        if (input.plus(num++).md5().startWithFiveZeroes()) {
            println(num - 1)
            break
        }
    }
}

fun String.md5():String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}

fun String.startWithFiveZeroes() = this.startsWith("00000")