import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion
val junitPlatformVersion = "1.6.1"
val spekVersion = "2.0.10"
val mockkVersion = "1.9.3"

group = "fr.lapostoj"
version = "0.0.1-SNAPSHOT"

plugins {
	idea
	kotlin("jvm") version "1.3.70"
	kotlin("plugin.spring") version "1.3.70"
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

idea.project {
    vcs = "Git"
    jdkName = "1.8"
}

tasks.compileKotlin {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.compileTestKotlin {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.test {
	useJUnitPlatform {
		includeEngines("spek2")
	}
}

repositories {
    jcenter()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("org.springframework.boot:spring-boot-starter-web") {
		exclude(module = "spring-boot-starter-tomcat")
	}
	implementation("org.springframework.boot:spring-boot-starter-jetty")


	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	testImplementation("org.jetbrains.kotlin:kotlin-test")
	testImplementation("org.spekframework.spek2:spek-dsl-jvm:${spekVersion}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:${mockkVersion}")
	testImplementation("org.junit.platform:junit-platform-commons:${junitPlatformVersion}")
	testImplementation("org.junit.platform:junit-platform-engine:${junitPlatformVersion}")
	testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:${spekVersion}")
}
