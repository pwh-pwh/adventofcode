package demo

import kotlin.math.exp
import kotlin.random.Random

/**
 * @Auther: coderpwh
 * @Date: 2024/2/22 16:25
 * @Description:
 */
/**
 *
 * @param weights 权重
 * @param bias 偏置
 * @param learningRate 学习率
 */
class NeuralNetwork(
    val weights: DoubleArray = doubleArrayOf(Random.nextDouble(), Random.nextDouble()),
    var bias: Double = Random.nextDouble(),
    val learningRate: Double = 0.1
) {

    // 训练模型
    fun train(inputs: List<DoubleArray>, outputs: List<Int>, epoch: Int) = repeat(epoch) {
        assert(inputs.size == outputs.size)
        inputs.zip(outputs).forEach { (input, output) ->
            // 前向传播
            val prediction = predict(input)
            // 计算损失
            val error = output - prediction
            // 反向传播更新权重和偏置
            weights.indices.forEach { i ->
                weights[i] += error * input[i] * learningRate * prediction.derivative()
            }
            bias += error * learningRate * prediction.derivative()
        }
    }

    // 使用训练好的模型进行预测
    fun predict(input: DoubleArray) =
        (input.zip(weights).sumOf { (inputValue, weight) -> inputValue * weight } + bias).sigmoid()
}

// sigmoid激活函数
inline fun Double.sigmoid() = 1.0 / (1.0 + exp(-this))


inline fun Double.derivative() = this * (1.0 - this)

fun main() {
    // 训练数据
    val inputs = List(200) { doubleArrayOf(Random.nextDouble(), Random.nextDouble()) }
    val outputs = inputs.map {
        if ((it[0] + it[1]) >= 1) 1 else 0
    }
    val network = NeuralNetwork()
    network.train(inputs, outputs, 200)
    val testInput = List(50) { doubleArrayOf(Random.nextDouble(), Random.nextDouble()) }
    var count = 0
    var sucNum = 0
    testInput.forEach {
        val result = network.predict(it)
        val expect = if ((it[0] + it[1]) >= 1) 1 else 0
        // 是否正确
        val isCorrect = expect == if (result >= 0.5) 1 else 0
        count++
        if (isCorrect) sucNum++
        println("预测数据: ${it[0]},${it[1]},预测结果：${result} 是否正确:  ${isCorrect}")
    }
    println("预测准确率: ${sucNum * 1.0 / count}")
}