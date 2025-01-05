plugins{
    id("vshulker-conventions")
    id("com.gradleup.shadow") version "8.3.5"
}

dependencies{
    compileOnly(libs.paper.api)
    api(project(":api"))
}

tasks.build{
    dependsOn("shadowJar")
}

tasks.shadowJar{
    archiveBaseName.set("vshulker")
    archiveClassifier.set("")
    archiveVersion.set(version.toString())
}