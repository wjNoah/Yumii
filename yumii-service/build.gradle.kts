// Business logic / use cases
plugins {
    `java-library`
}

dependencies {
    api(project(":yumii-domain"))
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")
}
