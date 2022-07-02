package com.lollipoppp.vector

import androidx.compose.ui.graphics.asSkiaPath
import androidx.compose.ui.graphics.vector.PathParser

object VectorHelper {

    /**
     * @param viewportWidth 窗口的大小，相对于画布数据而言
     * @param viewportHeight 窗口的大小，相对于画布数据而言
     * @param pathData 绘制的数据，路径的数据
     */
    fun parse(
        viewportWidth: Int,
        viewportHeight: Int,
        pathData: String
    ): Vector.Frame {
        return parse(VectorData(viewportWidth, viewportHeight, pathData))
    }

    /**
     * @param data 矢量图的描述信息
     */
    fun parse(
        data: VectorData
    ): Vector.Frame {
        return Vector.Frame(
            data.viewportWidth,
            data.viewportHeight,
            PathParser().parsePathString(data.pathData).toPath().asSkiaPath()
        )
    }


}


