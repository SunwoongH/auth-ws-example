import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":domain"))
    // implementation(project(":support:jwt"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${Versions.SPRING_BOOT}")
    runtimeOnly("com.h2database:h2:${Versions.H2}")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
