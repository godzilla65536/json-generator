package com.github.godzilla65536.json.generator.extension

import org.everit.json.schema.Schema

fun Schema.getFieldName() = this.schemaLocation.split("/").last()