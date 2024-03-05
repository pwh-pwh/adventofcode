package y2015.day14

import java.io.File

/**
 * @Auther: coderpwh
 * @Date: 2024/3/5 16:05
 * @Description:
 */
fun main() {

    /**
     * s 速度 r 运行时间 d 休息时间 total 总时间
     */
    fun doCal(s:Int,r:Int,d:Int,total:Int):Int {
        var count = 0
        var sum = 0
        var t = total
        while (t > 0) {
            t -= if (count % 2==0) {
                if (t<=r) {
                    sum+=(t*s)
                    break
                }
                sum+=(r*s)
                r
            } else {
                if (t<=d) {
                    break
                }
                d
            }
            count++
        }
        return sum
    }

    val max = File("src/y2015/day14/day14.txt").readLines()
        .maxOf {
            str ->
            val numberRegex = """\d+""".toRegex()
            val (s,r,d) = numberRegex.findAll(str).map {
                it.value.toInt()
            }.toList()
            doCal(s,r,d,2503)
        }
    println("max is $max")
}
