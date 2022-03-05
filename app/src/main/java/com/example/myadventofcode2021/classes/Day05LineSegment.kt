package com.example.myadventofcode2021.classes

class Day05LineSegment {

    var x1: Int = 0
    var x2: Int = 0
    var y1: Int = 0
    var y2: Int = 0

    constructor(line: String) {
        val coordinates = line.split(" -> ")
        var positions = coordinates[0].split(",")

        x1 = positions[0].toInt()
        y1 = positions[1].toInt()

        positions = coordinates[1].split(",")
        x2 = positions[0].toInt()
        y2 = positions[1].toInt()
    }
}