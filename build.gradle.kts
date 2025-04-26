

plugins {
    id("java")
    id("application")
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ru.kpfu.itis"
version = "1.0-SNAPSHOT"

val springVersion: String by project
val springSecurityVersion: String by project
val jakartaVersion: String by project
val hibernateVersion: String by project
val postgresVersion: String by project

repositories {
    mavenCentral()
    mavenLocal()

}

application{
    mainClass = "ru.kpfu.itis.Main"
}

dependencies {
    implementation("org.springframework:spring-webmvc:$springVersion")
    implementation("org.springframework:spring-jdbc:$springVersion")
    implementation("org.springframework:spring-orm:$springVersion")
    implementation("org.springframework:spring-context-support:$springVersion")
    implementation("org.springframework.security:spring-security-core:$springSecurityVersion")
    implementation("org.springframework.security:spring-security-web:$springSecurityVersion")
    implementation("org.springframework.security:spring-security-config:$springSecurityVersion")
    implementation("org.springframework.security:spring-security-taglibs:$springSecurityVersion")
    implementation("org.springframework.boot:spring-boot-starter-freemarker")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("jakarta.servlet:jakarta.servlet-api:$jakartaVersion")
    implementation("org.hibernate:hibernate-core:$hibernateVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("org.springframework.data:spring-data-jpa:3.4.4")
    implementation("com.mchange:c3p0:0.10.2")
    implementation("org.freemarker:freemarker:2.3.34")
    implementation("org.projectlombok:lombok:")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")


    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}