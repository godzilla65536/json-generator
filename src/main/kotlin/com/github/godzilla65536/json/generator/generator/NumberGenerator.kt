package com.github.godzilla65536.json.generator.generator

import com.github.godzilla65536.json.generator.extension.getFieldName
import com.github.godzilla65536.json.generator.rule.NumberRule
import com.github.godzilla65536.json.generator.rule.Rule
import org.everit.json.schema.NumberSchema
import kotlin.random.Random
import kotlin.reflect.KClass

class NumberGenerator(
    override val schema: NumberSchema,
    override val rules: Map<KClass<out Rule>, List<Rule>>
) : JsonValueGenerator<Number> {

    override fun generate(): Number {
        val fieldName = schema.getFieldName()
        if (fieldName != "items") {
            val rule = rules[NumberRule::class]!!.find { it.field == fieldName } as NumberRule?
            if (rule != null) return rule.value
        }
        return Random.nextInt(Int.MAX_VALUE)
    }

}