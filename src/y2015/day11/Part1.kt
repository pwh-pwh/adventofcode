package y2015.day11

/**
 * @Auther: coderpwh
 * @Date: 2024/2/19 13:47
 * @Description:
 */
fun main() {
    var input = "cqjxjnds"
    var result = input
    do {
        result = result.strIncrement()
    } while (!result.isValid())
    println(result)
}

private fun String.strIncrement(): String {
    var carry = 1
    val sb = StringBuilder()
    this.toList().reversed().forEach {
        var n = it.code + carry
        if (n.toChar() > 'z') {
            n = 'a'.code
            carry = 1
        } else {
            carry = 0
        }
        sb.append(n.toChar())
    }
    return sb.reversed().toString()
}

private fun String.isValid(): Boolean = this.threeIncreaseLetter() && this.twoConsecutiveLetter() && this.noIOL()

private fun String.threeIncreaseLetter() = ('a'..'z').windowed(3)
    .any {
        this.contains(it.joinToString(""))
    }

private fun String.noIOL() = !this.contains('i') && !this.contains('o') && !this.contains('l')

private fun String.twoConsecutiveLetter(): Boolean {
    val set = mutableSetOf<Char>()
    this.windowed(2)
        .forEach {
            if (it[0] == it[1]) {
                set.add(it[0])
            }
        }
    return set.size >= 2
}