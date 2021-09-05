package com.github.godzilla65536.json.generator

import com.github.godzilla65536.json.generator.generator.JsonValueGenerator
import com.github.godzilla65536.json.generator.rule.Rule
import org.everit.json.schema.Schema
import org.everit.json.schema.loader.SchemaLoader
import org.json.JSONObject
import org.reflections.Reflections
import java.io.File
import java.net.URL
import kotlin.reflect.KClass

class JsonGenerator private constructor(
    schema: Schema,
    rules: List<Rule>
) {

    private val schema: Schema
    private val rules: Map<KClass<out Rule>, List<Rule>>

    init {
        this.schema = schema
        this.rules = mapRules(rules)
    }

    fun generate() = JsonValueGenerator.getGenerator(schema, rules).generate().toString()

    private fun mapRules(rules: List<Rule>): MutableMap<KClass<out Rule>, List<Rule>> {
        val rulesMap = Rule::class.java
            .let { c -> Reflections(c.packageName).getSubTypesOf(c) }
            .map { it.kotlin }
            .associateWith { emptyList<Rule>() }
            .toMutableMap()
        rules
            .groupBy { it::class }
            .mapValues { entry -> entry.value.asReversed().distinctBy { it.field } }
            .let { rulesMap.putAll(it) }
        return rulesMap
    }

    @Suppress("MemberVisibilityCanBePrivate")
    companion object {

        fun from(schema: Schema, rules: List<Rule>? = null) =
            JsonGenerator(schema, rules ?: emptyList())

        fun from(schema: String, rules: List<Rule>? = null) =
            JSONObject(schema)
                .let { SchemaLoader.load(it) }
                .let { from(it, rules) }

        fun from(schemaURL: URL, rules: List<Rule>? = null) =
            from(schemaURL.readText(), rules)

        fun from(schemaFile: File, rules: List<Rule>? = null) =
            from(schemaFile.readText(), rules)

    }

}