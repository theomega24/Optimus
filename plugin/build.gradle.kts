plugins {
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("org.cadixdev.licenser") version "0.5.1"
}

dependencies {
    compileOnly("com.destroystokyo.paper", "paper", "1.16.5-R0.1-SNAPSHOT")
    compileOnly("org.geysermc.floodgate", "api", "2.0-SNAPSHOT")

    implementation(project(":optimus-api"))
    implementation("org.bstats", "bstats-bukkit", "2.2.1")
    implementation("cloud.commandframework", "cloud-paper", "1.4.0")
    implementation("cloud.commandframework", "cloud-annotations", "1.4.0")
    implementation("cloud.commandframework", "cloud-minecraft-extras", "1.4.0")
    implementation("net.kyori", "adventure-text-minimessage", "4.1.0-SNAPSHOT")
}

tasks {
    java {
        sourceCompatibility = JavaVersion.toVersion(11)
        targetCompatibility = JavaVersion.toVersion(11)
    }

    processResources {
        expand("version" to project.version)
    }

    license {
        header = rootProject.rootDir.resolve("HEADER")
        include("**/*.java")
    }

    shadowJar {
        archiveClassifier.set("")
        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
        destinationDirectory.set(rootProject.rootDir.resolve("build").resolve("libs"))
        from(rootProject.projectDir.resolve("LICENSE"))

        minimize {
            exclude { it.moduleName == "optimus-api" }
        }

        listOf(
            "org.bstats",
            "cloud.commandframework",
            "net.kyori.examination",
            "io.leangen.geantyref",
            "org.checkerframework",
            "net.kyori.adventure.text.minimessage"
        ).forEach { relocate(it, "me.notom3ga.optimus.libs.$it") }
        minimize()
    }

    build {
        dependsOn(shadowJar)
    }
}


