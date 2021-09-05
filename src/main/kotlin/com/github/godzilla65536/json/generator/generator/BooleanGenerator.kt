package com.github.godzilla65536.json.generator.generator

import com.github.godzilla65536.json.generator.rule.Rule
import org.everit.json.schema.BooleanSchema
import kotlin.random.Random
import kotlin.reflect.KClass

class BooleanGenerator(
    override val schema: BooleanSchema,
    override val rules: Map<KClass<out Rule>, List<Rule>>
) : JsonValueGenerator<Boolean> {

    override fun generate() = Random.nextBoolean()

}