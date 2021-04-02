plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "me.notom3ga"
version = "0.0-ALPHA"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.opencollab.dev/maven-snapshots")
    mavenLocal()
}

dependencies {
    compileOnly("com.destroystokyo.paper", "paper-api", "1.16.5-R0.1-SNAPSHOT")
    compileOnly("com.destroystokyo.paper", "paper", "1.16.5-R0.1-SNAPSHOT")

    compileOnly("org.geysermc", "floodgate-bukkit", "1.0-SNAPSHOT")

    implementation("org.tomlj", "tomlj", "1.0.0")
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
            "org.tomlj",
            "cloud.commandframework"
        ).forEach { relocate(it, "me.notom3ga.optimus.libs.$it") }
        minimize()
    }

    build {
        dependsOn(shadowJar)
    }
}
