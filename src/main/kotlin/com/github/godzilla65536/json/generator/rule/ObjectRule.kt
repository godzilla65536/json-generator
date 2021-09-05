package com.github.godzilla65536.json.generator.rule

data class ObjectRule(
    override val field: String,
    val value: String
) : Rule