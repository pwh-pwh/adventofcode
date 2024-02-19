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
    val charArray = this.toCharArray()
    for (i in charArray.indices.reversed()) {
        if (charArray[i] == 'z') {
            charArray[i] = 'a'
        } else {
            charArray[i]++
            break
        }
    }
    return String(charArray)
}

private fun String.isValid(): Boolean =
    this.hasThreeIncreasingLetters() && this.hasTwoNonOverlappingPairs() && this.doesNotContainIOL()

private fun String.hasThreeIncreasingLetters(): Boolean {
    for (i in 0 until length - 2) {
        if (this[i] + 1 == this[i + 1] && this[i] + 2 == this[i + 2]) {
            return true
        }
    }
    return false
}

private fun String.doesNotContainIOL(): Boolean = none { it in "iol" }

private fun String.hasTwoNonOverlappingPairs(): Boolean {
    val regex = Regex("""(.)\1""")
    return regex.findAll(this).map { it.value[0] }.toSet().size >= 2
}