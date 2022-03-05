package com.example.myadventofcode2021.helpers

import com.example.myadventofcode2021.classes.Day05LineSegment
import kotlin.math.abs

object Day05LineSegmentHelper {

    fun map(size: Int, ls: Day05LineSegment, map: Array<Int>, isDiagonal: Boolean) {
        with(ls) {
            when {
                x1 == x2 -> {
                    val min = minOf(y1, y2)
                    val max = maxOf(y1, y2)
                    for (i in min..max) {
                        map[size * i + x1]++
                    }
                }
                y1 == y2 -> {
                    val min = minOf(x1, x2)
                    val max = maxOf(x1, x2)
                    for (i in min..max) {
                        map[size * y1 + i]++
                    }
                }
                isDiagonal && (y1 < y2 && x1 > x2 || y1 > y2 && x1 < x2) -> {
                    val max = abs(y2 - y1)
                    val minY = minOf(y1, y2)
                    val maxX = maxOf(x1, x2)
                    for (i in 0..max) {
                        map[size * (minY + i) + (maxX - i)]++
                    }
                }
                isDiagonal && (y1 > y2 && x1 > x2 || y1 < y2 && x1 < x2) -> {
                    val max = abs(y2 - y1)
                    val minY = minOf(y1, y2)
                    val minX = minOf(x1, x2)
                    for (i in 0..max) {
                        map[size * (minY + i) + (minX + i)]++
                    }
                }
            }
        }
    }
}