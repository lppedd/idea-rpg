@file:Suppress("ConvertLambdaToReference")

plugins {
  java
  kotlin("jvm") version "1.5.0"
  id("io.freefair.lombok") version "6.0.0-m2"
  id("org.jetbrains.intellij") version "0.7.3"
}

group = "com.github.lppedd"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = sourceCompatibility
}

sourceSets["main"].java.srcDirs("src/main/gen")

dependencies {
  implementation(kotlin("stdlib"))
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

intellij {
  version = "2021.1.1"
  setPlugins("java")
}

tasks.getByName<Test>("test") {
  useJUnitPlatform()
}

tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
  changeNotes(
    """
      Add change notes here.<br>
      <em>most HTML tags may be used</em>"""
  )
}
