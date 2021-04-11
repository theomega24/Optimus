import java.io.ByteArrayOutputStream

plugins {
    `java-library`
}

allprojects {
    group = "me.notom3ga"
    version = "1.0-ALPHA-${getGitCommit()}"
}

subprojects {
    apply<JavaLibraryPlugin>()

    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://repo.opencollab.dev/maven-snapshots")
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
