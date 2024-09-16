group = "com.anastasiia"
version = "0.0.1-SNAPSHOT"

plugins {
    val kotlinVersion = "1.9.24"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.telegram:telegrambots:6.9.7.1")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.mapstruct:mapstruct:1.6.0")
    implementation("org.mapstruct:mapstruct-processor:1.6.0")
    implementation("org.flywaydb:flyway-core:10.17.1")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("com.auth0:java-jwt:4.4.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    runtimeOnly("org.flywaydb:flyway-database-postgresql:10.17.1")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin.compilerOptions.freeCompilerArgs.addAll("-Xjsr305=strict")
java.toolchain.languageVersion = JavaLanguageVersion.of(21) // todo: change to 21

tasks.withType<Test> {
    useJUnitPlatform()
}
