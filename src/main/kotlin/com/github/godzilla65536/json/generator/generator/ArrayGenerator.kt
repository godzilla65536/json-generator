package com.github.godzilla65536.json.generator.generator

import com.github.godzilla65536.json.generator.generator.JsonValueGenerator.Companion.getGenerator
import com.github.godzilla65536.json.generator.rule.Rule
import org.everit.json.schema.ArraySchema
import org.json.JSONArray
import kotlin.reflect.KClass

class ArrayGenerator(
    override val schema: ArraySchema,
    override val rules: Map<KClass<out Rule>, List<Rule>>
) : JsonValueGenerator<JSONArray> {

    override fun generate(): JSONArray {
        val allItemSchema = schema.allItemSchema
        return JSONArray().put(getGenerator(allItemSchema, rules).generate())
    }

}