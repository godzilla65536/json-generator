package com.github.godzilla65536.json.generator.rule

data class StringRule(
    override val field: String,
    val value: String,
) : Rule