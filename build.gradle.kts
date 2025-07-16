plugins {
    id("java")
    id("maven-publish")
}

group = "dev.redcrew"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.13.1")
    implementation("org.projectlombok:lombok:1.18.34")
    implementation("org.jetbrains:annotations:15.0")
    annotationProcessor("org.jetbrains:annotations:15.0")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    //  The Artifacts we are going to upload
    publications {
        create<MavenPublication>("maven") {
            artifact("build/libs/packager-${version}.jar") {
                extension = "jar"
            }
        }
    }

    repositories {
        maven {
            name = "nexus"
            url = uri("https://nexus.redcrew.dev/repository/maven-snapshots/")
            credentials {
                username = project.findProperty("repoUser") as String
                password = project.findProperty("repoPassword") as String
            }
        }
    }
}