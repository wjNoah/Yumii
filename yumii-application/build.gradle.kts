// Spring Boot entry point: wires all modules and bundles the Angular build into the jar
plugins {
    java
    alias(libs.plugins.spring.boot)
}

val webui: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {
    implementation(project(":yumii-endpoint"))
    implementation(project(":yumii-service"))
    runtimeOnly(project(":yumii-persistence"))

    implementation("org.springframework.boot:spring-boot-yumii-webmvc")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.springframework.boot:spring-boot-h2console")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Skip with: ./gradlew build -PskipWebui
    if (!providers.gradleProperty("skipWebui").isPresent) {
        webui(project(path = ":yumii-webui", configuration = "webuiDist"))
    }
}

// Angular build output -> classpath:/static (served by Spring Boot)
tasks.processResources {
    from(webui) {
        into("static")
    }
}
