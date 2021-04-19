import java.io.ByteArrayOutputStream

plugins {
    `java-library`
}

allprojects {
    group = "me.notom3ga"
    version = "1.0-BETA-${System.getenv("BUILD_NUMBER") ?: getGitCommit()}"
}

subprojects {
    apply<JavaLibraryPlugin>()

    java {
        sourceCompatibility = JavaVersion.toVersion(11)
        targetCompatibility = JavaVersion.toVersion(11)
    }

    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://repo.opencollab.dev/maven-snapshots")
        maven("https://libraries.minecraft.net/")
        mavenLocal()
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = Charsets.UTF_8.name()
        }
    }
}

fun getGitCommit(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}
