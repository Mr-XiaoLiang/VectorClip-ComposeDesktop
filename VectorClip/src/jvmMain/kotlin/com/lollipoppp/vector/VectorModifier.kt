package com.lollipoppp.vector

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun Modifier.clip(
    vectorInfo: Vector
) = this.clip(VectorShape(vectorInfo))

@Composable
fun Modifier.clip(
    vectorInfo: VectorData
) = this.clip(VectorHelper.parse(vectorInfo))

@Composable
fun Modifier.clip(
    viewportWidth: Int,
    viewportHeight: Int,
    pathData: String
) = this.clip(VectorData(viewportWidth, viewportHeight, pathData))
