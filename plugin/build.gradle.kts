plugins {
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("org.cadixdev.licenser") version "0.5.1"
}

dependencies {
    compileOnly("com.destroystokyo.paper", "paper", "1.16.5-R0.1-SNAPSHOT")
    compileOnly("org.geysermc.floodgate", "api", "2.0-SNAPSHOT")

    implementation(project(":optimus-api"))
    implementation("me.lucko", "commodore", "1.9")
    implementation("net.kyori", "adventure-text-minimessage", "4.1.0-SNAPSHOT")
    implementation("org.bstats", "bstats-bukkit", "2.2.1")
}

tasks {
    processResources {
        expand("version" to project.version)
    }

    shadowJar {
        archiveClassifier.set("")
        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
        destinationDirectory.set(rootProject.rootDir.resolve("build").resolve("libs"))
        from(rootProject.projectDir.resolve("LICENSE"))

        minimize {
            exclude { it.moduleName == "optimus-api" }
        }

        dependencies {
            exclude(dependency("net.kyori:adventure-api"))
            exclude(dependency("com.mojang:brigadier"))
        }

        listOf(
            "me.lucko",
            "net.kyori.examination",
            "net.kyori.adventure.key",
            "net.kyori.adventure.text.minimessage",
            "org.bstats",
        ).forEach { relocate(it, "me.notom3ga.optimus.libs.$it") }
        minimize()
    }

    build {
        dependsOn(shadowJar)
    }
}

license {
    header = rootProject.rootDir.resolve("HEADER")
    include("**/*.java")
}
