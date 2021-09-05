package com.github.godzilla65536.json.generator.rule

data class NumberRule(
    override val field: String,
    val value: Number
) : Rule