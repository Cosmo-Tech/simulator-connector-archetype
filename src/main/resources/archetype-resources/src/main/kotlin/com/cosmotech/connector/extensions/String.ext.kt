package com.cosmotech.connector.extensions

import com.cosmotech.connector.constants.SCHEMA_AS_JSON_SUFFIX
import com.cosmotech.connector.constants.SHOW_TABLE_PREFIX

/**
 * @return Return a ADT model name from a ADT model id
 */
fun String.getModelNameFromModelId(): String {
    return this.split(":").last().split(";").first()
}

/**
 * @return return the .show table query
 */
fun String.toShowTableQuery(): String {
    return SHOW_TABLE_PREFIX.plus(this).plus(SCHEMA_AS_JSON_SUFFIX)
}


