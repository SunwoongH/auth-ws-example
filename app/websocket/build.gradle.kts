import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.spring")
}

dependencies {
    // implementation(project(":app:support:auth"))
    implementation(project(":core"))

    implementation("org.springframework.boot:spring-boot-starter-websocket:${Versions.SPRING_BOOT}")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
