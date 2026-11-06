plugins {
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.node.gradle) apply false
}

allprojects {
    group = "com.example.yumii"
    version = "0.0.1-SNAPSHOT"
}

val javaVersion = libs.versions.java.get()

// Shared config for every Java module (applied only where the java plugin is used)
subprojects {
    plugins.withType<JavaPlugin> {
        extensions.configure<JavaPluginExtension> {
            toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))
        }

        dependencies {
            "implementation"(platform(project(":yumii-platform")))
            "testImplementation"("org.springframework.boot:spring-boot-yumii-test")
            "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
        }

        tasks.withType<JavaCompile>().configureEach {
            options.encoding = "UTF-8"
            options.compilerArgs.add("-parameters")
        }

        tasks.withType<Test>().configureEach {
            useJUnitPlatform()
        }
    }
}
