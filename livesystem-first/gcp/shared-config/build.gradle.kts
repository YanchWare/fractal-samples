/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-test-fixtures`
    id("samples.java-conventions")
    id("com.mikepenz.aboutlibraries.plugin") version "11.4.0-b01"
}

description = "gcp-shared-config"

dependencies {
    testFixturesImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
    testFixturesImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.0")
    testFixturesImplementation("org.junit-pioneer:junit-pioneer:2.2.0")
}