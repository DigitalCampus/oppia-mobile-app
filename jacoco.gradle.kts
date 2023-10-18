
apply(plugin = "jacoco")

tasks {
    val jacocoTestReport by creating(JacocoReport::class) {
        dependsOn( "testDebugUnitTest", "connectedCheck")

        group = "Reporting"
        description = "Generate Jacoco coverage reports for Debug build"

        reports {
            html.required.set(true)
            xml.required.set(true)
        }

        val mainSrc = "${project.projectDir}/src/main/java"

        sourceDirectories.from(files(arrayOf(mainSrc)))
        classDirectories.from("${project.buildDir}/tmp/kotlin-classes/debug")

        executionData.setFrom(fileTree("${buildDir}/outputs/").matching {
            setIncludes(arrayOf("code_coverage/debugAndroidTest/connected/**/*.ec", "unit_test_code_coverage/debugUnitTest/*.exec").asIterable())
        })
    }
}

