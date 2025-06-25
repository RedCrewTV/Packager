plugins {
    id("java")
}

group = "dev.redcrew"
version = "1.0.0"

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