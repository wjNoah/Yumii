// Central dependency versions (BOM) for all Java modules
plugins {
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform(libs.spring.boot.bom))
}
