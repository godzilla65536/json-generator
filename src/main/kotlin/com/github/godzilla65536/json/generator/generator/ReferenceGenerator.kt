package com.github.godzilla65536.json.generator.generator

import com.github.godzilla65536.json.generator.generator.JsonValueGenerator.Companion.getGenerator
import com.github.godzilla65536.json.generator.rule.Rule
import org.everit.json.schema.ReferenceSchema
import kotlin.reflect.KClass

class ReferenceGenerator(
    override val schema: ReferenceSchema,
    override val rules: Map<KClass<out Rule>, List<Rule>>
) : JsonValueGenerator<Any> {

    override fun generate(): Any = getGenerator(schema.referredSchema, rules).generate()!!

}