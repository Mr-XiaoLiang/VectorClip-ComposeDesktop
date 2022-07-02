package com.lollipoppp.vector

import androidx.compose.ui.graphics.Matrix
import org.jetbrains.skia.Matrix44
import org.jetbrains.skia.Path

sealed class Vector {
    abstract val path: Path
    abstract fun onBoundsChanged(width: Int, height: Int, offsetX: Int, offsetY: Int)

    class Stack: Vector() {

        private val frameList = ArrayList<Frame>()
        override val path = Path()

        fun addFrame(index: Int, frame: Frame): Stack {
            frameList.add(index, frame)
            return this
        }

        fun addFrame(frame: Frame): Stack {
            frameList.add(frame)
            return this
        }

        fun removeFrame(frame: Frame): Stack {
            frameList.remove(frame)
            return this
        }

        fun removeAt(index: Int): Stack {
            frameList.removeAt(index)
            return this
        }

        fun addFrame(data: VectorData): Stack {
            addFrame(VectorHelper.parse(data))
            return this
        }

        override fun onBoundsChanged(width: Int, height: Int, offsetX: Int, offsetY: Int) {
            path.reset()
            frameList.forEach {
                it.onBoundsChanged(width, height, offsetX, offsetY)
                path.addPath(it.path)
            }
        }
    }

    /**
     * 矢量图的解析数据
     */
    class Frame(
        private val viewportWidth: Int,
        private val viewportHeight: Int,
        private val srcPath: Path
    ): Vector() {

        private var lastWidth = viewportWidth
        private var lastHeight = viewportHeight
        private var lastOffsetX = 0
        private var lastOffsetY = 0
        override val path = Path().apply {
            addPath(srcPath)
        }

        override fun onBoundsChanged(width: Int, height: Int, offsetX: Int, offsetY: Int) {
            if (width == lastWidth && height == lastHeight
                && lastOffsetX == offsetX && lastOffsetY == offsetY
            ) {
                return
            }
            if (width == 0 || height == 0) {
                path.reset()
                return
            }
            val widthWeight = width * 1F / viewportWidth
            val heightWeight = height * 1F / viewportHeight
            val matrix = Matrix()
            matrix.scale(widthWeight, heightWeight)
            matrix.translate(offsetX.toFloat(), offsetY.toFloat())
            val matrix33 = Matrix44(*matrix.values).asMatrix33()
            path.reset()
            path.addPath(srcPath, matrix33)
        }

    }
}