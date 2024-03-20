package y2015.day20

/**
 * @Auther: coderpwh
 * @Date: 2024/3/20 15:51
 * @Description:
 */
fun main() {
    val target = 36000000
    val max = 1000000
    var houses = IntArray(max)
    var answer = 0
    for (elf in 1 until max) {
        var visited = elf
        while (visited < max) {
            houses[visited] += elf * 10
            visited += elf
        }
    }
    for (i in 0 until max) {
        if (houses[i] >= target) {
            answer = i
            break
        }
    }

    println(answer)
}