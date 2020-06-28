#의존성

##Spring

##JPA

###H2

#### 로컬 테스트 환경 구축

###MARIA DB

#### 실제 데이터베이스

#구현사항

## Test
1. 테스트 환경에서 유닛테스트뿐만 아니라 실제 JPA의 테스트를 위해 H2 를 이용한 테스트가 필요하다고 생각합니다.
2. 그래들에서 testImplementation으로 H2를 잡아주어 로컬에서 데이터베이스를 이용한 테스트 환경을 사용할 수 있도록 구축하였습니다.
3. application.test.properties에 테스트 관련한 셋팅을 설정하였습니다.
    1. 아직 테스트환경이나 메인 개발 환경에서 오버라이딩을 할 상황이 없기 때문에 메인 프로퍼티는 빈 파일입니다.
    2. test 모듈아래 configuration 에서 ServiceWithDatabaseTest를 이용하여 test의 환경설정을 불러오도록 하였습니다.
    
## Main
1. profile을 dv로 하여 데이터베이스와 연결할 수 있도록 하였습니다.
 또한 첫 개발환경이기 때문에 dv에 설정은 
 `spring.jpa.hibernate.ddl-auto=create-drop`
 을 이용하여 초기 개발환경을 구축하였습니다.
 
 `-> 이후에는 이 부분을 update를 이용하여 update 쿼리를 확인하거나, 인덱스쿼리를 확인 할 때 쓸 수 있습니다.`
  
 `-> validate 옵션을 적용하면 현재 entity가 데이터베이스에 잘 맞는지 확인하는 작업을 거치기 때문에 실수하는 과정을 줄 일 수 있습니다.`
 
 `-> 실제 운영환경에서는 none 을 이용하며 추가적으로 db 형상관리 툴을 이용하면 도움이 될수 있습니다.`
 
 참고 : https://java.ihoney.pe.kr/380



# 구동시키기위한 과정

- mysql_docker 이용하여 간단하게 구동
    
    `$ docker run --rm -d -v data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=test -p 3306:3306 --name database ps-mariadb:0.1`
    
    `개인적으로 커스텀하게 만들었기 때문에 official한 mariadb 를 사용해도 무방합니다.`
- mysql_docker 를 이용하여 접속
    
    `$ docker exec -it database /bin/bash`

- mysql 접속후 데이터베이스 UTF8 로 기본 설정

    `->$ CREATE DATABASE 데이타베이스_이름 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;`

    `->이후 이모티콘 같은 특수한 경우를 저장하기 위해 utf8mb4의 설정이 필요 할 수 있다.` 

- 계정 생성 및 데이터베이스 권한 획득
    
    1. `$ USE mysql;`
    
    2. `$ SELECT HOST, USER, PASSWORD FROM USER;`
    
    3. `$ CREATE USER '아이디'@'%' IDENTIFIED BY '비밀번호';`
    
    4. `$ GRANT ALL PRIVILEGES ON 데이터베이스.* TO '아이디'@'%';`
    
    5. `$ FLUSH PRIVILEGES;`
    
    참고 : https://wlsufld.tistory.com/40
    
    `application.dv.properties를 맞게 수정해야 정상 작동 할 수 있습니다.`
    
    "# jms_listener" 
