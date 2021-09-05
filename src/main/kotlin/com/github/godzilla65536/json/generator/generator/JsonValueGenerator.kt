package com.github.godzilla65536.json.generator.generator

import com.github.godzilla65536.json.generator.rule.Rule
import org.everit.json.schema.*
import java.util.function.Predicate
import kotlin.reflect.KClass

interface JsonValueGenerator<T> {

    val schema: Schema
    val rules: Map<KClass<out Rule>, List<Rule>>

    fun generate(): T

    companion object {

        fun getGenerator(schema: Schema, rules: Map<KClass<out Rule>, List<Rule>>): JsonValueGenerator<*> {
            return when (schema) {
                is StringSchema -> StringGenerator(schema, rules)
                is NumberSchema -> NumberGenerator(schema, rules)
                is BooleanSchema -> BooleanGenerator(schema, rules)
                is ObjectSchema -> ObjectGenerator(schema, rules)
                is ArraySchema -> ArrayGenerator(schema, rules)
                is EnumSchema -> EnumGenerator(schema, rules)
                is CombinedSchema -> {
                    when {
                        isEnumSchemaPredicate.test(schema) -> getGenerator(
                            schema.subschemas.filterIsInstance<EnumSchema>().single(), rules
                        )
                        else -> throw UnsupportedOperationException("Error while processing Combined schema: $schema")
                    }
                }
                else -> throw UnsupportedOperationException("Unknown schema found: ${schema::class}")
            }
        }

        private val isEnumSchemaPredicate: Predicate<CombinedSchema>
            get() = Predicate {
                val subSchemas = it.subschemas.map { c -> c::class }
                when {
                    subSchemas.size == 2
                            && subSchemas.contains(StringSchema::class)
                            && subSchemas.contains(StringSchema::class) -> true
                    else -> throw UnsupportedOperationException("Error while processing Combined schema: $it")
                }
            }

    }

}










