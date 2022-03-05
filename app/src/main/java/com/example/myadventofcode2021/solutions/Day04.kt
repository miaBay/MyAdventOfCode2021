package com.example.myadventofcode2021.solutions

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    Day04.solve()
}

object Day04: Solution() {
    override fun myOwnSolutionPart1(input: String): Any {
        val inputAsList = input.splitMultiline()
        val raffledNumbers = inputAsList
            .first()
            .split(",")
            .filter { it.isNotBlankOrEmpty() }
            .map { it.toInt() }

        val bingoCards: List<MutableList<Int>> = inputAsList
            .subList(1, input.splitMultiline().count()) //0 index as raffledNumbers and the 1 index as the new line...
            .filter { it.count() > 0 }
            .map { row -> row.split(" ").filter { it.isNotBlankOrEmpty() }.map { it.toInt() }.toMutableList() }

        val cardGrouping: Int = bingoCards.count() / 5 //given the bingo card is 5x5

        for (number in raffledNumbers) {

            for (i in 0 until cardGrouping) {

                var bingoCard = bingoCards.subList(i * 5, 5 + (i * 5))

                var skipColumnValidation = false

                //horizontal validation
                for (row in 0 until 5) {
                    for (column in 0 until 5) {
                        if (bingoCard[row][column] == number) {
                            bingoCard[row][column] = -1

                            skipColumnValidation = true
                            break
                        }
                    }
                }

                //vertical validation
                if (!skipColumnValidation) {
                    for (column in 0 until 5) {
                        for (row in 0 until 5) {
                            if (bingoCard[column][row] == number) {
                                bingoCard[column][row] = -1
                                break
                            }
                        }
                    }
                }

                //determine if all cells in the current row has been marked
                for (row in 0 until 5) {
                    var horizontalSum = bingoCard[row].sumOf { it }
                    if (horizontalSum == -5) {
                        return number * bingoCard.flatten().filter { it > 0 }.sumOf { it }
                    }
                }

                //determine if all cells in the current row has been marked
                for (column in 0 until 5) {
                    var verticalSum = bingoCard.sumOf { row -> row[column] }
                    if (verticalSum == -5) {
                        return number * bingoCard.flatten().filter { it > 0 }.sumOf { it }
                    }
                }
            }
        }

        return 0
    }

    override fun myOwnSolutionPart2(input: String): Any {
        val inputAsList = input.splitMultiline()
        val raffledNumbers = inputAsList
            .first()
            .split(",")
            .filter { it.isNotBlankOrEmpty() }
            .map { it.toInt() }

        val bingoCards: MutableList<MutableList<Int>> = inputAsList
            .subList(1, input.splitMultiline().count()) //0 index as raffledNumbers and the 1 index as the new line...
            .filter { it.count() > 0 }
            .map { row -> row.split(" ").filter { it.isNotBlankOrEmpty() }.map { it.toInt() }.toMutableList() }
            .toMutableList()

        var lastWinnerValue = 0

        for (number in raffledNumbers) {

            val cardCount: Int = bingoCards.count() / 5 //given the bingo card is 5x5
            val tobeRemovedBingoCards = ArrayList<List<Int>>()

            for (i in 0 until cardCount) {
                var bingoCard = bingoCards.subList(i * 5, 5 + (i * 5))

                //horizontal validation
                for (row in 0 until 5) {
                    for (column in 0 until 5) {
                        if (bingoCard[row][column] == number) {
                            bingoCard[row][column] = -1
                            break
                        }
                    }
                }

                //vertical validation
                /*if (!skipColumnValidation) {
                    for (column in 0 until 5) {
                        for (row in 0 until 5) {
                            if (bingoCard[column][row] == number) {
                                bingoCard[column][row] = -1
                                break
                            }
                        }
                    }
                }*/

                //determine if all cells in the current row has been marked
                for (row in 0 until 5) {
                    var horizontalSum = bingoCard[row].sumOf { it }
                    if (horizontalSum == -5) {
                        lastWinnerValue = number * bingoCard.flatten().filter { it > 0 }.sumOf { it }
                    }
                }

                //determine if all cells in the current column has been marked
                for (column in 0 until 5) {
                    var verticalSum = bingoCard.sumOf { row -> row[column] }
                    if (verticalSum == -5) {
                        lastWinnerValue = number * bingoCard.flatten().filter { it > 0 }.sumOf { it }
                    }
                }

                if (lastWinnerValue > 0) {
                    tobeRemovedBingoCards.addAll(bingoCard)
                }
            }

            if (tobeRemovedBingoCards.isNotEmpty())
                bingoCards.removeAll(tobeRemovedBingoCards)
        }

        return lastWinnerValue
    }

    override fun optimizedSolutionPart1(input: String): Any =
        com.example.myadventofcode2021.toddginsberg.Day04(input.splitMultiline()).solvePart1()

    override fun optimizedSolutionPart2(input: String): Any =
        com.example.myadventofcode2021.toddginsberg.Day04(input.splitMultiline()).solvePart2()
}