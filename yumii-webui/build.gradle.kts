// Angular frontend, built through Gradle via the node-gradle plugin
import com.github.gradle.node.npm.task.NpmTask

plugins {
    base
    alias(libs.plugins.node.gradle)
}

node {
    version.set(libs.versions.node.get())
    download.set(true) // Gradle downloads Node itself, no global install needed
}

val buildWebui by tasks.registering(NpmTask::class) {
    group = "build"
    description = "Builds the Angular app (ng build)."
    dependsOn(tasks.npmInstall)
    args.set(listOf("run", "build"))
    inputs.dir("src")
    inputs.dir("public")
    inputs.files("package.json", "package-lock.json", "angular.json", "tsconfig.json", "tsconfig.app.json")
    outputs.dir(layout.buildDirectory.dir("dist/browser"))
}

val testWebui by tasks.registering(NpmTask::class) {
    group = "verification"
    description = "Runs the Angular unit tests."
    dependsOn(tasks.npmInstall)
    args.set(listOf("run", "test", "--", "--watch=false"))
}

tasks.assemble { dependsOn(buildWebui) }
tasks.check { dependsOn(testWebui) }

// Exposed to :yumii-application, which copies it into the jar
val webuiDist: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

artifacts {
    add(webuiDist.name, layout.buildDirectory.dir("dist/browser")) {
        builtBy(buildWebui)
    }
}
