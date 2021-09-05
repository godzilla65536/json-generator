package com.github.godzilla65536.json.generator.generator

import com.github.godzilla65536.json.generator.extension.getFieldName
import com.github.godzilla65536.json.generator.rule.Rule
import com.github.godzilla65536.json.generator.rule.StringRule
import org.apache.commons.lang3.RandomStringUtils
import org.everit.json.schema.Schema
import org.everit.json.schema.StringSchema
import kotlin.reflect.KClass

class StringGenerator(
    override val schema: StringSchema,
    override val rules: Map<KClass<out Rule>, List<Rule>>
) : JsonValueGenerator<String> {

    override fun generate(): String {
        val fieldName = schema.getFieldName()
        if (fieldName != "items") {
            val rule = rules[StringRule::class]!!.find { it.field == fieldName } as StringRule?
            if (rule != null) return rule.value
        }
        return RandomStringUtils.randomAlphanumeric(10)
    }

}