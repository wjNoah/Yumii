// REST controllers + DTOs (the HTTP API under /api)
plugins {
    `java-library`
}

dependencies {
    implementation(project(":yumii-service"))
    implementation("org.springframework.boot:spring-boot-yumii-webmvc")
    implementation("org.springframework.boot:spring-boot-yumii-validation")
}
