package com.razacx.aoc.y2020.day7

fun getBagsContainingColorRecursively(rules: List<BagContentRule>, color: String): Set<String> {
    val bagsThatContainColorDirectly = rules.filter { bag -> bag.contents.any { it.color == color } }.map { it.color }
    return bagsThatContainColorDirectly.map { getBagsContainingColorRecursively(rules, it) }.flatten().toSet() + bagsThatContainColorDirectly
}

fun getTotalBagCountContainedInBag(rules: List<BagContentRule>, color: String): Int {
    val rule = rules.find { it.color == color }!!
    val bagCountContainedDirectlyInThisBag = rule.contents.sumBy { it.amount }
    val bagCountContainedInContent = rule.contents.sumBy { getTotalBagCountContainedInBag(rules, it.color) * it.amount }
    return bagCountContainedDirectlyInThisBag + bagCountContainedInContent
}

fun parseBagContentRules(rules: String): List<BagContentRule> {
    return rules.split("\n").filter { !it.isBlank() }.map { rule ->
        val (ruleColor, content) = rule.split(" bags contain ")
        if (content.trim() != "no other bags.") {
            BagContentRule(ruleColor, parseBagContent(content))
        } else {
            BagContentRule(ruleColor, emptyList())
        }
    }
}

private fun parseBagContent(content: String): List<BagContent> {
    return content.split(",")
        .map {
            it
                .replace(".", "")
                .replace("bags", "")
                .replace("bag", "")
                .trim()
            // We should be left with something like '9 faded blue' now
        }
        .map {
            val (_, amount, color) = "(\\d*) (.*)".toRegex().matchEntire(it)!!.groupValues
            BagContent(color, amount.toInt())
        }
}

data class BagContentRule(val color: String, val contents: List<BagContent>)
data class BagContent(val color: String, val amount: Int)
