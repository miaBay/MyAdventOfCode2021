package com.example.myadventofcode2021.classes

import kotlin.math.abs

class Day05Line(x1: Int, y1: Int, x2: Int, y2: Int) {
    var points: MutableList<Point> = mutableListOf()

    init {
        val iX = (x2 - x1) / maxOf(1, abs(x2 - x1))
        val iY = (y2 - y1) / maxOf(1, abs(y2 - y1))
        var x = x1
        var y = y1


        val maxOfX = maxOf(abs(x2) - abs(x1), abs(x1) - abs(x2))
        val maxOfY = maxOf(abs(y2) - abs(y1), abs(y1) - abs(y2))
        val max = maxOf(maxOfX, maxOfY)

        for (i in 0..max) {
            points += Point(x, y)

            x += iX
            y += iY
        }
    }
}