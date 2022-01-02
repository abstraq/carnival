import java.time.Year

plugins {
    id("java")
    id("application")
    id("org.sonarqube") version "3.3"
    id("com.github.hierynomus.license") version "0.16.1"
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
        exclude("com.google.code.findbugs", "jsr305")
        exclude("org.jetbrains", "annotations")
    }

    implementation("com.zaxxer", "HikariCP", "5.0.0") {
        exclude("org.slf4j", "slf4j-api")
    }

    implementation("ch.qos.logback", "logback-classic", "1.2.7") {
        exclude("org.slf4j", "slf4j-api")
    }

    implementation("org.checkerframework", "checker-qual", "3.21.0")

    implementation("org.postgresql", "postgresql", "42.3.1")
}

application {
    mainClass.set("me.abstraq.carnival.Carnival")
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

    mapping("java", "SLASHSTAR_STYLE")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = Charsets.UTF_8.name()
    options.release.set(17)
}