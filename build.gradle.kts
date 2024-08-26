plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.apache.commons:commons-lang3:3.16.0")
    implementation("commons-io:commons-io:2.16.1")
    implementation("org.springframework.boot:spring-boot-starter:3.3.3")
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.3")
    implementation("org.projectlombok:lombok:1.18.34")
}

tasks.test {
    useJUnitPlatform()
}
