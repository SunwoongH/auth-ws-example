import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":app:support:auth"))
    implementation(project(":app:websocket"))
    implementation(project(":core"))
    implementation(project(":support:yaml"))

    implementation("org.springframework.boot:spring-boot-starter-web:${Versions.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-validation:${Versions.SPRING_BOOT}")
}

tasks {
    withType<Jar> { enabled = false }
    withType<BootJar> { enabled = true }
}
