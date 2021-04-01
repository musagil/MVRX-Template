package com.delivery.hero.network

/**
 * Marks a request for caching
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Cacheable
