package com.lollipoppp.vector

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class VectorShape(
    private val vector: Vector
) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        vector.onBoundsChanged(size.width.toInt(), size.height.toInt(), 0, 0)
        return Outline.Generic(vector.path.asComposePath())
    }
}