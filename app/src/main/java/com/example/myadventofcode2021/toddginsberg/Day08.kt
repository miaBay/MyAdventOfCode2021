package com.example.myadventofcode2021.toddginsberg

class Day08(input: List<String>) {

    private val inputRows = input.map { InputRow.of(it) }

    fun solvePart1(): Int =
        inputRows.sumOf { row ->
            row.digitSegments.takeLast(4).count { it.size in setOf(2, 3, 4, 7) }
        }

    fun solvePart2(): Int = inputRows.sumOf { row -> row.calculateValue() }

    private class InputRow(val digitSegments: List<Set<Char>>) {

        private val digitValues = discoverMappings()

        companion object {
            fun of(input: String): InputRow =
                InputRow(
                    input
                        .split(" ")
                        .filterNot { it == "|" }
                        .map { it.toSet() }
                )
        }

        fun calculateValue(): Int =
            (digitValues.getValue(digitSegments[10]) * 1000) +
                    (digitValues.getValue(digitSegments[11]) * 100) +
                    (digitValues.getValue(digitSegments[12]) * 10) +
                    digitValues.getValue(digitSegments[13])

        private infix fun Set<Char>.overlaps(that: Set<Char>): Boolean =
            this.containsAll(that)

        private fun discoverMappings(): Map<Set<Char>, Int> {
            val digitToString = Array<Set<Char>>(10) { emptySet() }

            digitToString[1] = digitSegments.first { it.size == 2 }
            digitToString[4] = digitSegments.first { it.size == 4 }
            digitToString[7] = digitSegments.first { it.size == 3 }
            digitToString[8] = digitSegments.first { it.size == 7 }

            digitToString[3] = digitSegments
                .filter { it.size == 5 }
                .first { it overlaps digitToString[1] }

            digitToString[9] = digitSegments
                .filter { it.size == 6 }
                .first { it overlaps digitToString[3] }

            digitToString[0] = digitSegments
                .filter { it.size == 6 }
                .filter { it overlaps digitToString[1] && it overlaps digitToString[7] }
                .first { it != digitToString[9] }

            digitToString[6] = digitSegments
                .filter { it.size == 6 }
                .first { it != digitToString[0] && it != digitToString[9] }

            digitToString[5] = digitSegments
                .filter { it.size == 5 }
                .first { digitToString[6] overlaps it }

            digitToString[2] = digitSegments
                .filter { it.size == 5 }
                .first { it != digitToString[3] && it != digitToString[5] }

            return digitToString.mapIndexed { index, chars -> chars to index }.toMap()
        }
    }
}