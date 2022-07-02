import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("maven-publish")
}

group = "com.lollipoppp.vector"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}

publishing {
//    publications {
//        create<MavenPublication>("maven") {
//            groupId = group.toString()
//            artifactId = "vector"
//            version = version.toString()
//
//            from(components["java"])
//        }
//    }
    repositories {
        maven {
            url = uri("file:///Users/lollipop/Develop/Java/LocalMaven")
        }
    }
}
