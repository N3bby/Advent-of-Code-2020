package com.razacx.aoc.y2020.util

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList

class IOUtils {

    companion object {
        fun readLines(path: String): List<String> {
            return BufferedReader(InputStreamReader(javaClass.classLoader.getResourceAsStream(path)))
                .lines()
                .toList()
        }

        fun readText(path: String): String {
            return BufferedReader(InputStreamReader(javaClass.classLoader.getResourceAsStream(path)))
                .readText()
        }
    }

}

