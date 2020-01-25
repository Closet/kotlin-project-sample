dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation(project(":domain-user"))
    implementation(project(":domain-board"))
    api(project(":common"))
}