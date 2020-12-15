package com.cosmotech.connector.constants

// ############################
// ## Common
// ############################

/**
 * Environment variable in which the Azure tenant is stored
 */
const val ENVVAR_AZURE_TENANT_ID = "AZURE_TENANT_ID"

/**
 * Environment variable in which the Azure tenant is stored
 */
const val ENVVAR_AZURE_CLIENT_ID = "AZURE_CLIENT_ID"

/**
 * Environment variable in which the technical Azure account username is stored
 */
const val ENVVAR_AZURE_USERNAME = "AZURE_USERNAME"

/**
 * Environment variable in which the technical Azure account password is stored
 */
const val ENVVAR_AZURE_PASSWORD = "AZURE_PASSWORD"

// ############################
// ## Azure Digital Twins
// ############################

/**
 * Query that retrieve all digital twins declared in an ADT
 */
const val SELECT_ALL_DT = "SELECT * FROM digitaltwins"

/**
 * Probe suffix used to filter Probe Digital Twins
 */
const val PROBE_SUFFIX = "Probe"

/**
 * Environment variable in which the ADT endpoint is stored
 */
const val ENVVAR_ADT_INSTANCE_URL = "ADT_INSTANCE_URL"

/**
 * Environment variable in which the absolute export path for CSVs is stored
 */
const val ENVVAR_ABSOLUTE_PATH_EXPORT_CSV_FILE = "EXPORT_CSV_FILE_ABSOLUTE_PATH"

/**
 * Default header cell name for digital twins
 */
val digitalTwinDefaultHeader = mutableListOf("id")

/**
 * Default header cell name for relations
 */
val relationshipDefaultHeader = mutableListOf("source", "target", "name")

// ############################
// ## Azure Data Explorer
// ############################

/**
 * Create table instruction prefix
 */
const val CREATE_TABLE_PREFIX = ".create table "

/**
 * Show table instruction prefix
 */
const val SHOW_TABLE_PREFIX = ".show table "

/**
 * Schema as json instruction suffix
 */
const val SCHEMA_AS_JSON_SUFFIX = " schema as json"

/**
 * Environment variable in which the ADX Cluster endpoint is stored
 */
const val ENVVAR_ADX_CLUSTER_PATH = "ADX_CLUSTER_PATH"

/**
 * Environment variable in which the ADX Cluster endpoint is stored
 */
const val ENVVAR_ADX_APP_ID = "ADX_APP_ID"

/**
 * Environment variable in which the ADX targeted database name is stored
 */
const val ENVVAR_ADX_DATABASE_NAME = "ADX_DATABASE_NAME"


