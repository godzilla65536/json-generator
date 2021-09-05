package com.github.godzilla65536.json.generator.rule

data class ArrayRule(
    override val field: String,
    val value: String
) : Rule