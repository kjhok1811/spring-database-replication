## 설명
- 해당 코드는 Database Replication이 구성된 AWS RDS쪽으로 Read와 Write의 부하를 분산하게끔 Spring의 DataSource를 설정하는 예제코드입니다.  이 예제코드에서는 다음과 같은 기능을 다룹니다
  - <a href='https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/datasource/lookup/AbstractRoutingDataSource.html'>AbstractRoutingDataSource</a>를 이용하여 1개의 Write용 DataSource와 N개의 Read용 DataSource 설정
  - 어플리케이션에서 N개의 Read용 데이터베이스에 로드밸런싱

<br>

## 프로젝트 환경
- 프로젝트 환경구성은 다음과 같습니다.
  - JAVA 17
  - Spring Boot 2.6.4
  - AWS RDS Aurora

<br>

## 테스트 하는법
- 테스트를 하는방법은 <a href='https://kim-jong-hyun.tistory.com/125'>여기</a>에 정리해두었습니다. 안되는 부분이 있으면 코멘트 부탁드리겠습니다.
