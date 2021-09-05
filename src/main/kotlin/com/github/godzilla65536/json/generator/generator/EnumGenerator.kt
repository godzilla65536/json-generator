package com.github.godzilla65536.json.generator.generator

import com.github.godzilla65536.json.generator.rule.Rule
import org.everit.json.schema.EnumSchema
import kotlin.reflect.KClass

class EnumGenerator(
    override val schema: EnumSchema,
    override val rules: Map<KClass<out Rule>, List<Rule>>
) : JsonValueGenerator<Any> {

    override fun generate(): Any = schema.possibleValues.random()

}
