package demo

import java.awt.Color
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

/**
 * 绘制Mandelbrot集
 */
fun main() {
    val width = 800
    val height = 800
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val maxIterations = 300
    val zoom = 150.0

    for (y in 0 until height) {
        for (x in 0 until width) {
            var zx = 0.0
            var zy = 0.0
            val cX = (x - 400) / zoom
            val cY = (y - 400) / zoom
            var iter = maxIterations
            while (zx * zx + zy * zy < 4.0 && iter > 0) {
                val tmp = zx * zx - zy * zy + cX
                zy = 2.0 * zx * zy + cY
                zx = tmp
                iter--
            }
            val color = if (iter > 0) Color.HSBtoRGB(iter / 256f, 1f, iter.toFloat() / maxIterations) else 0
            image.setRGB(x, y, color)
        }
    }

    // Save the image to a file
    ImageIO.write(image, "png", File("mandelbrot_set.png"))
    println("Mandelbrot set image created successfully.")
}