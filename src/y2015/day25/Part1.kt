package y2015.day25

/**
 * @Auther: coderpwh
 * @Date: 2024/4/3 10:09
 * @Description:
 */
fun main() {
    fun Long.nextNum():Long {
        return (this * 252533) % 33554393
    }

    fun getIndex(row:Int, col:Int):Int {
        var index = 1
        var r = 1
        var c = 1
        while (r != row || c != col) {
            if (r==1) {
                r = c+1
                c = 1
            } else {
                r--
                c++
            }
            index++
        }
        return index
    }

    val row = 2978
    val col = 3083
    var index = getIndex(row, col)
    println("index: $index")
    var result = 20151125L
    repeat(index-1) {
        result = result.nextNum()
    }
    println(result)
}

/*
   | 1   2   3   4   5   6
---+---+---+---+---+---+---+
 1 |  1   3   6  10  15  21
 2 |  2   5   9  14  20
 3 |  4   8  13  19
 4 |  7  12  18
 5 | 11  17
 6 | 16
 */