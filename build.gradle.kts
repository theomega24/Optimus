import java.io.ByteArrayOutputStream

plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

fun getGitCommit(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

group = "me.notom3ga"
version = "1.0-ALPHA-${getGitCommit()}"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    mavenLocal()
}

dependencies {
    compileOnly("com.destroystokyo.paper", "paper-api", "1.16.5-R0.1-SNAPSHOT")
    compileOnly("com.destroystokyo.paper", "paper", "1.16.5-R0.1-SNAPSHOT")

    implementation("org.bstats", "bstats-bukkit", "2.2.1")
    implementation("cloud.commandframework", "cloud-paper", "1.4.0")
    implementation("cloud.commandframework", "cloud-annotations", "1.4.0")
    implementation("cloud.commandframework", "cloud-minecraft-extras", "1.4.0")
}

tasks {
    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    processResources {
        expand("version" to project.version)
    }

    shadowJar {
        archiveClassifier.set("")
        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
        destinationDirectory.set(rootProject.rootDir.resolve("build").resolve("libs"))

        listOf(
            "org.bstats",
            "cloud.commandframework",
            "net.kyori",
            "io.leangen.geantyref",
            "org.checkerframework"
        ).forEach { relocate(it, "me.notom3ga.optimus.libs.$it") }
        minimize()
    }

    build {
        dependsOn(shadowJar)
    }
}
