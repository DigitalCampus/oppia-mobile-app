import java.util.Properties
import java.io.FileInputStream
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.android.build.gradle.internal.tasks.factory.dependsOn
import java.util.Locale

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("org.sonarqube") version "4.4.1.3373"
}

apply(from = "../jacoco.gradle.kts")

android {
    val runtimeProps = loadRuntimeProps()

    namespace = "org.digitalcampus.oppiamobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.digitalcampus.mobile.learning"
        minSdk = 21
        targetSdk = 34
        versionCode = 112
        versionName = "8.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore.jks")
            storePassword = System.getenv("SIGNING_STORE_PASSWORD")
            keyAlias = System.getenv("SIGNING_KEY_ALIAS")
            keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
        }
    }

    buildTypes {

        all {
            for (field in runtimeProps.keys()) {
                val value = runtimeProps.getProperty(field as String, "")

                val fieldType = when {
                    value.lowercase(Locale.getDefault()) == "true" || value.lowercase(Locale.getDefault()) == "false"  -> "boolean"
                    value.toIntOrNull() != null -> "int"
                    else -> "String"
                }

                buildConfigField(fieldType, field, if (fieldType == "String") "\"$value\"" else value  )
            }
        }

        debug {
            multiDexEnabled = true
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
            isDebuggable = true
        }

        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            applicationVariants.all {
                outputs.all {
                    if (name.contains("release")) {
                        (this as BaseVariantOutputImpl).outputFileName = "OppiaMobile-v$versionName.apk"
                    }
                }
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

}

dependencies {

//    val workVersion = "2.7.1"
    val roomVersion = "2.6.0"
//    val fragmentVersion = "1.5.4"
    val hiltVersion = "2.48.1"
    val appcompatVersion = "1.6.1"
//    val espressoVersion = "3.5.1"
    val kotlinVersion = "1.8.20"
    val coroutinesVersion = "1.7.3"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.appcompat:appcompat:$appcompatVersion")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.preference:preference-ktx:1.2.1")

    implementation("commons-codec:commons-codec:1.16.0")

    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    // TESTING
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("org.jacoco:org.jacoco.core:0.8.8")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")

    // Kotlin Dependencies
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}

fun loadRuntimeProps() : Properties {
    val runtimeProps = Properties()
    runtimeProps.load(FileInputStream(rootProject.file("oppia-default.properties")))

    val oppiaPropsFile = rootProject.file("custom.properties")
    if (oppiaPropsFile.canRead()) {
        logger.lifecycle("Fetching properties from external file")
        runtimeProps.load(FileInputStream(oppiaPropsFile))
    } else {
        logger.error("No properties file found. Using default values.")
    }

    return runtimeProps
}

ktlint {
    android.set(true)
    version.set("0.50.0")
    ignoreFailures.set(true)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
    }
}

tasks.sonar.dependsOn("ktlintCheck")
sonar {
    properties {
        property("sonar.projectName", "Oppia Mobile APP")
        property("sonar.projectKey", "DigitalCampus_oppia-mobile-app")
        property("sonar.organization", "oppiamobile")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.projectVersion", android.defaultConfig.versionName)
        property("sonar.tests", "src/test/java, src/androidTest/java")
        property("sonar.sourceEncoding", "UTF-8")
        property(
            "sonar.kotlin.ktlint.reportPaths",
                "build/reports/ktlint/ktlintAndroidTestSourceSetCheck/ktlintAndroidTestSourceSetCheck.xml," +
                "build/reports/ktlint/ktlintKotlinScriptCheck/ktlintKotlinScriptCheck.xml," +
                "build/reports/ktlint/ktlintMainSourceSetCheck/ktlintMainSourceSetCheck.xml," +
                "build/reports/ktlint/ktlintTestSourceSetCheck/ktlintTestSourceSetCheck.xml",
        )
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.junit.reportPaths", "**/test-results/**/*.xml")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml")
    }
}
