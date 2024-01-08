# Spring-Security-JWT
Spring-Security와 JWT를 사용한 로그인 API구현

# 초기화 작업
```kts
//build.gradle.kts 파일
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.9.21" //코틀린 버전을 상수로 정의해주고
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
    //상수로 값을 대입했음
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies { //의존성 관리
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//JAP를 사용하기위한 allOpen, noArg추가
allOpen {
	annotation("jakarta.persistence.Entity")
}
//plugin.spring에서 Open 해주는 것 외에 추가로 Open해줄 것 명시
noArg {
	annotation("jakarta.persistence.Entity")
}
//매개변수가 없는 생성자를 자동으로 추가


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

```

```yml
#src/main/resources/application.yml파일
server: #서버 관련 설정
  port: 8080 #포트번호
  servlet:
    context-path: /
    encoding:
      charset: UTF-8 #인코딩시 문자 집합
      enabled: true #HTTP 인코딩 지원 여부
      force: true //HTTP요청과 응답에서 문자 집합에 인코딩을 강제로 할지 여부
spring:
  datasource: #DB세팅
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/study
    username: root
    password: 1234
  jpa:
    open-in-view: true 
    hibernate:
      ddl-auto: create //기존테이블 삭제 후 다시 생성
    properties:
      hibernate:
        show_sql: false ## System.out에 sql로그 출력
        format_sql: true #sql를 보기좋게 줄맞춤
        highlight_sql: true #sql색상 표시 추가
logging: #로그인 관련 정보들
  pattern:
    console: "[%d{HH:mm:ss.SSS}][%-5level][%logger.%method:line%line] - %msg%n" #기본 로그 형태 지정
  level:
    org:
      hibernate:
        SQL: debug #logger에 sql로그 출력
        type.descriptor.sql: trace #trace로 하면 Sql에 바인딩 되는 값을 확인
```