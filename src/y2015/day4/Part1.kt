package y2015.day4

import java.security.MessageDigest

/**
 * @Auther: coderpwh
 * @Date: 2024/2/7 14:09
 * @Description:
 */
fun main() {
    val input = "yzbqklnj"
    val result = generateSequence(1) { it + 1 }
        .first { (input + it).md5().startsWith("00000") }

    println(result)
}

fun String.md5(): String = MessageDigest.getInstance("MD5")
    .digest(this.toByteArray())
    .joinToString("") { "%02x".format(it) }