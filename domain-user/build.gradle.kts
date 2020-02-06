dependencies {
    implementation( project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation("org.mariadb.jdbc", "mariadb-java-client", "2.5.2")
    implementation("org.springframework:spring-webmvc")

}