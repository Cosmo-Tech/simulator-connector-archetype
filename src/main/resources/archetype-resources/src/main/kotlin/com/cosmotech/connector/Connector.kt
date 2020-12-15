package com.cosmotech.connector

/**
 * Cosmo Solution Connector interface
 */
interface Connector<T> {
  /**
   * Build a client to interact with the targeted data storage
   * @return the client
   */
  fun buildClient(): T
}