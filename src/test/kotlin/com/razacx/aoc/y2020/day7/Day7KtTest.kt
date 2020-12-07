package com.razacx.aoc.y2020.day7

import com.razacx.aoc.y2020.util.IOUtils.Companion.readText
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day7KtTest {

    @Test
    fun `Correct outermost bag colors are returned using the example input`() {
        val input = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()
        val expectedOutermostBags = setOf(
            "bright white",
            "muted yellow",
            "dark orange",
            "light red"
        )
        assertEquals(
            expectedOutermostBags,
            getBagsContainingColorRecursively(parseBagContentRules(input), "shiny gold")
        )
    }

    @Test
    fun `BagContentRules are parsed correctly from the (partial) example input`() {
        val input = """
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dotted black bags contain no other bags.
        """.trimIndent()
        val rules = parseBagContentRules(input)
        assertEquals(
            listOf(
                BagContentRule("dark orange", listOf(BagContent("bright white", 3), BagContent("muted yellow", 4))),
                BagContentRule("bright white", listOf(BagContent("shiny gold", 1))),
                BagContentRule("shiny gold", listOf(BagContent("dark olive", 1), BagContent("vibrant plum", 2))),
                BagContentRule("dotted black", emptyList()),
            ),
            rules
        )
    }

    @Test
    fun `get total bag count contained in shiny gold bag using example input`() {
        val input = """
            shiny gold bags contain 2 dark red bags.
            dark red bags contain 2 dark orange bags.
            dark orange bags contain 2 dark yellow bags.
            dark yellow bags contain 2 dark green bags.
            dark green bags contain 2 dark blue bags.
            dark blue bags contain 2 dark violet bags.
            dark violet bags contain no other bags.
        """.trimIndent()
        assertEquals(126, getTotalBagCountContainedInBag(parseBagContentRules(input), "shiny gold"))
    }

    @Test
    fun `get count of bags that would eventually contain shiny gold bag using puzzle input`() {
        val rules = parseBagContentRules(readText("day7/rules.txt").replace("\r", ""))
        println("Day 7 Part 1 | Count of unique bags that would eventually contain a shiny gold bag: " + getBagsContainingColorRecursively(rules, "shiny gold").size)
    }

    @Test
    fun `get total count of bags contains in a shiny gold bag using puzzle input`() {
        val rules = parseBagContentRules(readText("day7/rules.txt").replace("\r", ""))
        println("Day 7 Part 2 | Total count of bags contained within a shiny gold bag: " + getTotalBagCountContainedInBag(rules, "shiny gold"))
    }

}
