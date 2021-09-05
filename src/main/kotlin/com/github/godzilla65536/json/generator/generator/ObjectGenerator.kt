package com.github.godzilla65536.json.generator.generator

import com.github.godzilla65536.json.generator.generator.JsonValueGenerator.Companion.getGenerator
import com.github.godzilla65536.json.generator.rule.Rule
import org.everit.json.schema.ObjectSchema
import org.json.JSONObject
import kotlin.reflect.KClass

class ObjectGenerator(
    override val schema: ObjectSchema,
    override val rules: Map<KClass<out Rule>, List<Rule>>
) : JsonValueGenerator<JSONObject> {

    override fun generate(): JSONObject {
        val jsonObject = JSONObject()
        schema.propertySchemas
            .map { jsonObject.put(it.key, getGenerator(it.value, rules).generate()) }
        return jsonObject
    }

}