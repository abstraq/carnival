import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

import java.time.Year

plugins {
    id("java")
    id("org.sonarqube") version "3.3"
    id("com.github.hierynomus.license") version "0.16.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.abstraq"
version = "1.0.0"

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/groups/public/") }
}

dependencies {
    implementation("net.dv8tion", "JDA", "5.0.0-alpha.3") {
        exclude("club.minnced", "opus-java")
    }

    implementation("com.zaxxer", "HikariCP", "5.0.0") {
        exclude("org.slf4j", "slf4j-api")
    }

    implementation("ch.qos.logback", "logback-classic", "1.2.7") {
        exclude("org.slf4j", "slf4j-api")
    }

    implementation("org.postgresql", "postgresql", "42.3.1")
}

sonarqube {
    properties {
        property("sonar.projectKey", "abstraq_carnival")
        property("sonar.organization", "abstraq")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

license {
    headerURI = uri("HEADER")
    strictCheck = true

    ext["projectName"] = "Carnival"
    ext["name"] = "abstraq"
    ext["email"] = "abstraq@outlook.com"
    ext["year"] = Year.now().toString()

    include("**/*.java")
    mapping("java", "SLASHSTAR_STYLE")
}

tasks.create<ConfigureShadowRelocation>("relocateShadowJar") {
    target = tasks["shadowJar"] as ShadowJar
    prefix = "me.abstraq.carnival.libs"
}

tasks.named<ShadowJar>("shadowJar").configure {
    dependsOn(tasks["relocateShadowJar"])
    mergeServiceFiles()
    minimize()
    archiveBaseName.set("carnival")
    archiveClassifier.set("")
    manifest {
        attributes["Main-Class"] = "me.abstraq.carnival.Carnival"
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = Charsets.UTF_8.name()
    options.release.set(17)
}