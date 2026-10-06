plugins {
    kotlin("jvm") version "2.3.21"
    `java-library`
    `maven-publish`
    id("org.jlleitschuh.gradle.ktlint") version "12.2.0"
}

group = "dev.jacobandersen"
version = file("version.txt").readText().trim()
description = "microformats2"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    api("org.jsoup:jsoup:1.23.2")
    api("tools.jackson.core:jackson-databind:3.1.5")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

ktlint {
    version.set("1.8.0")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifactId = "microformats2"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/marchland/microformats2")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: (project.findProperty("gpr.user") as String?)
                password = System.getenv("GITHUB_TOKEN") ?: (project.findProperty("gpr.token") as String?)
            }
        }
    }
}
