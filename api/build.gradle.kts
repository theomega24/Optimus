plugins {
    `maven-publish`
    id("org.cadixdev.licenser") version "0.5.1"
}

dependencies {
    compileOnlyApi("com.destroystokyo.paper", "paper-api", "1.16.5-R0.1-SNAPSHOT")
}

tasks {
    java {
        sourceCompatibility = JavaVersion.toVersion(8)
        targetCompatibility = JavaVersion.toVersion(8)
    }

    license {
        header = rootProject.rootDir.resolve("HEADER")
        include("**/*.java")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories.maven {
        url = uri("https://repo.notom3ga.me/releases")
        credentials(PasswordCredentials::class)
    }
}
