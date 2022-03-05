@file:Suppress("unused")
package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.File
import java.nio.file.Paths
import kotlin.time.milliseconds
import kotlin.time.toDuration

abstract class Solution {
    abstract fun myOwnSolutionPart1(input: String): Any

    abstract fun myOwnSolutionPart2(input: String): Any

    abstract fun optimizedSolutionPart1(input: String): Any

    abstract fun optimizedSolutionPart2(input: String): Any

    private val identifier: String = getClassName()

    private var tStart = 0L
    private var tEnd = 0L

    @RequiresApi(Build.VERSION_CODES.O)
    fun solve() {
        val input: String = retrieveInputs()

        startTimer()
        println("Solution for part 1: ${myOwnSolutionPart1(input)}")
        endTimer()
        println("Elapsed time: ${computeElapsedTime()}")
        println()

        startTimer()
        println("Solution for part 2: ${myOwnSolutionPart2(input)}")
        endTimer()
        println("Elapsed time: ${computeElapsedTime()}")
        println()

        println("")
        startTimer()
        println("Solution for optimized part 1: ${optimizedSolutionPart1(input)}")
        endTimer()
        println("Elapsed time: ${computeElapsedTime()}")
        println()

        startTimer()
        println("Solution for optimized part 2: ${optimizedSolutionPart2(input)}")
        endTimer()
        println("Elapsed time: ${computeElapsedTime()}")
        println()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun retrieveInputs(): String {
        val directoryPath = Paths.get("").resolve(INPUT_PATH).toAbsolutePath()
        return File("$directoryPath/$identifier.$INPUT_FILE_EXTENSION").readText()
    }

    private fun getClassName(): String = this::class.simpleName.toString()

    companion object {
        const val INPUT_PATH = "app/src/main/java/com/example/myadventofcode2021/resources"
        const val INPUT_FILE_EXTENSION = "txt"
    }

    private fun startTimer() {
        tStart = System.currentTimeMillis()
    }

    private fun endTimer() {
        tEnd = System.currentTimeMillis()
    }

    private fun computeElapsedTime() = (tEnd - tStart)
}

fun String.splitMultiline(): List<String> = split("\r\n")

fun List<String>.toIntList(): List<Int> = this.map(String::toInt)

fun String.toBase10(): Int = Integer.parseInt(this, 2)

fun String.isNotBlankOrEmpty(): Boolean = this.isNotBlank() && this.isNotEmpty()

fun List<Int>.toBase10(): Int {
    return this.joinToString("").toBase10()
}

fun <T, K> Collection<T>.countBy(keySelector: (T) -> K): Map<K, Int> {
    return this.groupingBy(keySelector).eachCount()
}

fun Long.triangular(): Long = ((this * (this +1)) /2)

fun Int.triangular(): Int = ((this * (this +1)) /2)

fun String.sortAlphabetically(): String = String(toCharArray().apply { sort() })