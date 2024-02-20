package demo

import java.awt.Color
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

/**
 * 绘制julia_set
 */
fun main() {
    val width = 800
    val height = 800
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val maxIterations = 300
    val zoom = 1.0
    val moveX = 0.0
    val moveY = 0.0
    val cX = -0.4
    val cY = 0.6

    for (y in 0 until height) {
        for (x in 0 until width) {
            var zx = 1.5 * (x - width / 2) / (0.5 * zoom * width) + moveX
            var zy = (y - height / 2) / (0.5 * zoom * height) + moveY
            var i = maxIterations
            while (zx * zx + zy * zy < 4 && i > 0) {
                val temp = zx * zx - zy * zy + cX
                zy = 2.0 * zx * zy + cY
                zx = temp
                i--
            }
            val colorValue = if (i > 0) Color.HSBtoRGB((maxIterations / i.toFloat()) % 1, 1f, i.toFloat() / maxIterations) else 0
            image.setRGB(x, y, colorValue)
        }
    }

    // Save the image to a file
    ImageIO.write(image, "png", File("julia_set.png"))
    println("Julia set image created successfully.")
}