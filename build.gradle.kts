import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("war")
}

group = "ru.kpfu.itis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()

}

application{
    mainClass = "ru.kpfu.itis.Main"
}

dependencies {

    implementation("org.springframework:spring-webmvc:6.2.3")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:11.0.5")

    implementation ("com.fasterxml.jackson.core:jackson-databind:2.15.2")



    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}