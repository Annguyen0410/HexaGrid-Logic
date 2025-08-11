package com.pixelbitst.hexagridlogic_

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.*

class HexagonGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Hexagon properties
    private val hexRadius = 80f
    private val hexHeight = hexRadius * 2
    private val hexWidth = sqrt(3.0) * hexRadius
    private val verticalSpacing = hexHeight * 0.75

    // Grid properties
    private val gridRows = 8
    private val gridCols = 6

    // Paint objects
    private val hexPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 4f
        color = Color.BLACK
    }

    // Colors
    private val defaultColor = Color.parseColor("#E0E0E0")
    private val selectedColor = Color.parseColor("#4CAF50")

    // Store hexagon data
    private data class HexagonCell(
        val centerX: Float,
        val centerY: Float,
        var isSelected: Boolean = false
    )

    private val hexagons = mutableListOf<HexagonCell>()

    init {
        initializeHexagons()
    }

    private fun initializeHexagons() {
        hexagons.clear()
        
        for (row in 0 until gridRows) {
            val hexagonsInRow = if (row % 2 == 0) gridCols else gridCols - 1
            val rowOffset = if (row % 2 == 0) 0f else (hexWidth / 2).toFloat()
            
            for (col in 0 until hexagonsInRow) {
                val centerX = (hexWidth / 2 + col * hexWidth + rowOffset).toFloat()
                val centerY = (hexRadius + row * verticalSpacing).toFloat()
                
                hexagons.add(HexagonCell(centerX, centerY))
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        for (hexagon in hexagons) {
            // Set color based on selection state
            hexPaint.color = if (hexagon.isSelected) selectedColor else defaultColor
            
            // Draw filled hexagon
            val hexPath = createHexagonPath(hexagon.centerX, hexagon.centerY)
            canvas.drawPath(hexPath, hexPaint)
            
            // Draw hexagon border
            canvas.drawPath(hexPath, strokePaint)
        }
    }

    private fun createHexagonPath(centerX: Float, centerY: Float): Path {
        val path = Path()
        
        for (i in 0..6) {
            val angle = Math.PI / 3 * i
            val x = centerX + hexRadius * cos(angle).toFloat()
            val y = centerY + hexRadius * sin(angle).toFloat()
            
            if (i == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }
        
        path.close()
        return path
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val touchX = event.x
            val touchY = event.y
            
            // Find the hexagon that was touched
            for (hexagon in hexagons) {
                if (isPointInHexagon(touchX, touchY, hexagon.centerX, hexagon.centerY)) {
                    // Toggle selection state
                    hexagon.isSelected = !hexagon.isSelected
                    invalidate() // Trigger redraw
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun isPointInHexagon(pointX: Float, pointY: Float, centerX: Float, centerY: Float): Boolean {
        val dx = abs(pointX - centerX)
        val dy = abs(pointY - centerY)
        
        // Simple distance-based hit detection
        val distance = sqrt((dx * dx + dy * dy).toDouble())
        return distance <= hexRadius
    }

    fun resetAllHexagons() {
        for (hexagon in hexagons) {
            hexagon.isSelected = false
        }
        invalidate() // Trigger redraw
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = (gridCols * hexWidth + hexWidth / 2).toInt()
        val minHeight = (gridRows * verticalSpacing + hexRadius).toInt()
        
        val width = resolveSize(minWidth, widthMeasureSpec)
        val height = resolveSize(minHeight, heightMeasureSpec)
        
        setMeasuredDimension(width, height)
    }
}
