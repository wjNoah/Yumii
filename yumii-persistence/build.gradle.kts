// JPA entities + adapters implementing domain ports
plugins {
    `java-library`
}

dependencies {
    implementation(project(":yumii-domain"))
    implementation("org.springframework.boot:spring-boot-yumii-data-jpa")
}
