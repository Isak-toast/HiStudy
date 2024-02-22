# SQL의 기본적인 쿼리 문법에 대해 설명해주세요.

> 작성자 : [정혜연](https://github.com/jeonglever)

<details>
<summary>Table of Contents</summary>

- [쿼리란 무엇인가](#쿼리란-무엇인가)
- [DDL](#DDL)
- [DML](#DML)
- [DCL](#DCL)
- [SELECT 쿼리 실행 순서](#SELECT-쿼리-실행-순서)
  </details>

---

## 쿼리란 무엇인가

"**SQL(Structured Query Language)**"은 데이터베이스 관리 시스템(DBMS)에서 데이터를 조작하고 관리하기 위해 사용하는 표준 프로그래밍 언어입니다.<br/>
SQL은 데이터를 조회, 삽입, 업데이트, 삭제하는 기본적인 작업을 수행하는 데 사용되는데, 크게 데이터 명령어(DDL), 데이터 조작어(DML), 데이터 제어어(DCL)로 종류를 나눠볼 수 있습니다.

![SQL나누기](https://velog.velcdn.com/images/tlsdnxkr/post/ce47c197-2840-40c0-81c3-f27f208fb62e/image.png)

## DDL

**DDL(Data Definition Language)**은 데이터베이스 스키마를 정의, 수정, 삭제하기 위한 SQL의 일부입니다. <br/>
DDL은 데이터베이스의 구조를 설계하는 데 사용되며, 테이블, 인덱스, 뷰, 스키마 등 데이터베이스 객체의 생성, 변경, 제거를 다룹니다.<br/>
DDL 명령어는 데이터 자체를 조작하지 않고 데이터베이스의 구조적인 부분에 영향을 미칩니다.

**CREATE**<br/>
CREATE 명령어는 새로운 데이터베이스, 테이블, 뷰, 인덱스 등의 데이터베이스 객체를 생성할 때 사용됩니다. <br/>
예를 들어, 새로운 테이블을 생성하는 경우 다음처럼 사용합니다..<br/>

```sql
CREATE TABLE a (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);
-- a 테이블
-- id : INT, PRIMARY KEY
-- name : VARCHAR(100)
-- email : BARCHAR(100)

CREATE TABLE b (
    log_id INT PRIMARY KEY,
    user_id INT,
    activity VARCHAR(255),
    log_time TIMESTAMP
);
-- b 테이블
-- log_id : INT, PRIMARY KEY
-- user_id : INT
-- activity : VARCHAR(255)
-- log_time : TIMESTAMP
```

**결과 :**<br/>
a 테이블
|컬럼명|데이터타입|제약조건|
|------|---|------|
|id|INT|PRIMARY KEY|
|name|VARCHAR(100)||
|email|BARCHAR(100)||

b 테이블
|컬럼명|데이터타입|제약조건|
|------|---|------|
|log_id|INT|PRIMARY KEY|
|user_id|INT||
|activity|VARCHAR(255)||
|log_time|TIMESTAMP||
<br/>

**ALTER**<br/>
ALTER 명령어는 이미 존재하는 데이터베이스 객체의 구조를 변경할 때 사용됩니다. <br/>
예를 들어, 테이블에 새로운 열을 추가하거나, 기존 열의 데이터 타입을 변경할 때 사용할 수 있습니다.<br/>

```sql
-- 테이블 a에 birthdate 열 추가
ALTER TABLE a
ADD birthdate DATE;
-- a 테이블
-- id : INT, PRIMARY KEY
-- name : VARCHAR(100)
-- email : BARCHAR(100)
-- (추가) birthdate : DATE

--또는

ALTER TABLE b
MODIFY activity TEXT; --변경
-- b 테이블
-- log_id : INT, PRIMARY KEY
-- user_id : INT
-- (수정) activity : TEXT
-- log_time : TIMESTAMP
```

**결과 :**<br/>
a 테이블
|컬럼명|데이터타입|제약조건|
|------|---|------|
|id|INT|PRIMARY KEY|
|name|VARCHAR(100)||
|email|BARCHAR(100)||
|birthdate|DATE||

b 테이블
|컬럼명|데이터타입|제약조건|
|------|---|------|
|log_id|INT|PRIMARY KEY|
|user_id|INT||
|activity|TEXT||
|log_time|TIMESTAMP||
<br/>

**DROP**<br/>
DROP 명령어는 존재하는 데이터베이스 객체를 삭제할 때 사용됩니다. <br/>
이 명령어는 객체를 영구적으로 삭제하기 때문에 사용 시 주의가 필요합니다.<br/>

```sql
DROP TABLE b;
```

**TRUNCATE**<br/>
TRUNCATE 명령어는 테이블 내의 모든 데이터를 빠르게 삭제하되, 테이블 구조와 정의는 유지할 때 사용됩니다. <br/>
TRUNCATE는 DELETE 명령어보다 훨씬 빠르게 작동하며, 로그를 남기지 않아 데이터 복구가 불가능합니다.<br/>

```sql
TRUNCATE TABLE a;
```

테이블 a의 모든 데이터가 삭제되었으나, 테이블 구조는 유지됩니다.<br/>

DDL은 데이터베이스 설계와 관리 과정에서 중요한 역할을 하며, 데이터베이스의 구조를 정의하는 데 필수적인 명령어 집합입니다. <br/>
데이터베이스의 구조적 변경이 필요할 때 적절히 활용됩니다.<br/>

## DML

**DML(Data Manipulation Language)**은 데이터베이스 내의 데이터를 조작하는 데 사용되는 SQL의 일부입니다. <br/>
주로 데이터를 삽입(INSERT), 조회(SELECT), 수정(UPDATE), 삭제(DELETE)하는 작업에 사용됩니다. <br/>
다음은 테이블 a와 b를 사용하여 DML 명령어의 예시와 그에 따른 결과를 설명합니다.<br/>

**현재 테이블 구조 :**<br/>
a 테이블
|컬럼명|데이터타입|제약조건|
|------|---|------|
|id|INT|PRIMARY KEY|
|name|VARCHAR(100)||
|email|BARCHAR(100)||
|birthdate|DATE||

b 테이블
|컬럼명|데이터타입|제약조건|
|------|---|------|
|log_id|INT|PRIMARY KEY|
|user_id|INT||
|activity|TEXT||
|log_time|TIMESTAMP||
<br/>

**INSERT INTO (데이터 삽입)**

```sql
INSERT INTO a (id, name, email, birthdate) VALUES (1, 'John Doe', 'john.doe@example.com', '1990-01-01');

INSERT INTO b (log_id, user_id, activity, log_time) VALUES (1, 1, 'Logged in', CURRENT_TIMESTAMP);

```

**결과:**<br/>
테이블 a에 새로운 사용자 정보가 추가됩니다.<br/>
| 컬럼명 | 컬럼값 |
| --------- | ----------------|
| id | 1 |
| name | 'John Doe' |
| email | 'john.doe@example.com' |
| birthdate | '1990-01-01' |
<br/>

테이블 b에 새로운 사용자 활동 정보가 추가됩니다.<br/>

| 컬럼명   | 컬럼값            |
| -------- | ----------------- |
| log_id   | 1                 |
| user_id  | 1                 |
| activity | 'Logged in'       |
| log_time | CURRENT_TIMESTAMP |

<br/>

**SELECT (데이터 조회)**<br/>
테이블 a에서 모든 사용자 정보 조회<br/>

```sql
SELECT * FROM a;
```

**결과:** 테이블 a의 모든 사용자 정보가 조회됩니다.<br/>

테이블 b에서 최근 로그 조회<br/>

```sql
SELECT * FROM b WHERE user_id = 1 ORDER BY log_time DESC LIMIT 1;
--WHERE : 조건에 맞는 특정 데이터를 조회할 때 사용합니다.
--ORDER BY : 조회 결과를 특정 열을 기준으로 정렬할 때 사용합니다. 기본적으로 오름차순으로 정렬되며, DESC를 추가하면 내림차순으로 정렬됩니다.
--LIMIT : 결과 중 몇개의 행을 보여줄 지 선택합니다.

```

**결과:** 지정된 user_id를 가진 사용자의 가장 최근 로그 정보가 조회됩니다.<br/>

**UPDATE (데이터 수정)**<br/>
테이블 a에서 사용자의 이메일 변경

```sql
UPDATE a SET email = 'new.email@example.com' WHERE id = 1;
```

**결과:** id가 1인 사용자의 이메일이 변경됩니다.
| 컬럼명 | 컬럼값 |
| --------- | ----------------|
| id | 1 |
| name | 'John Doe' |
| email | 'new.email@example.com' |
| birthdate | '1990-01-01' |
<br/>

**DELETE (데이터 삭제)**<br/>
테이블 b에서 특정 로그 삭제

```sql
DELETE FROM b WHERE log_id = 1;
```

**결과:** log_id가 1인 로그 정보가 테이블 b에서 삭제됩니다.<br/>
<br/>

**결과 반영 후의 데이터**<br/>
_테이블 a의 데이터_
| 컬럼명 | 컬럼값 |
| --------- | ----------------|
| id | 1 |
| name | 'John Doe' |
| email | 'new.email@example.com' |
| birthdate | '1990-01-01' |
<br/>

_테이블 b의 데이터_<br/>
log_id: 1의 로그 정보는 삭제되었습니다. <br/>
새로운 로그 정보가 추가되었을 경우를 제외하고, 현재 예시에서는 더 이상 데이터가 없습니다.<br/>
<br/>

DML 명령어를 사용하여 데이터를 조작하는 과정에서 데이터베이스 내의 정보가 실시간으로 변경됩니다. <br/>
이러한 명령어들은 데이터베이스를 유지보수하고 업데이트하는 데 필수적인 도구입니다.

## DCL

**DCL(Data Control Language)** 은 데이터베이스 시스템에서 데이터의 접근 권한을 제어하는 데 사용되는 SQL의 일부입니다. <br/>
DCL 명령어를 통해 사용자나 그룹에 데이터베이스 객체(테이블, 뷰 등)에 대한 권한을 부여하거나 회수할 수 있습니다. <br/>
주로 데이터베이스의 보안과 관련된 작업에 사용되며, 가장 일반적인 DCL 명령어에는 GRANT와 REVOKE가 있습니다.

**GRANT**
GRANT 명령어는 사용자나 그룹에 특정 작업을 수행할 수 있는 권한을 부여합니다. <br/>
예를 들어, 특정 사용자에게 특정 테이블에서 데이터를 조회할 권한을 주거나, <br/>
다른 사용자가 해당 테이블에 데이터를 삽입하거나 수정할 수 있도록 할 수 있습니다.<br/>

_예시_ : 특정 사용자에게 테이블 a에서 데이터를 조회할 권한을 부여하는 경우

```sql
GRANT SELECT ON a TO some_user;
```

이 명령어는 some_user 사용자가 테이블 a에서 데이터를 조회할 수 있는 권한을 받는다는 것을 의미합니다.<br/>
또한, 사용자에게 데이터베이스 내의 모든 테이블에 대한 INSERT 권한을 부여할 수도 있습니다:<br/>

```sql
GRANT INSERT ON DATABASE TO some_user;
-- INSERT 권한 부여
```

**REVOKE**<br/>
REVOKE 명령어는 이전에 부여된 권한을 회수합니다. <br/>
만약 사용자가 더 이상 특정 테이블에 접근할 필요가 없거나, 보안 정책 변경으로 인해 권한을 조정해야 할 경우 사용됩니다.<br/>

_예시_ : 특정 사용자로부터 테이블 a에 대한 조회 권한을 회수하는 경우

```sql
REVOKE SELECT ON a FROM some_user;
-- SELECT 권한 회수
```

이 명령어는 some_user 사용자가 더 이상 테이블 a에서 데이터를 조회할 수 없음을 의미합니다.<br/>

**DCL의 중요성**<br/>
DCL은 데이터베이스의 보안 관리에 매우 중요한 역할을 합니다. <br/>
적절한 권한 관리를 통해 민감한 데이터의 무단 접근을 방지하고, 데이터베이스의 무결성을 유지할 수 있습니다. <br/>
데이터베이스 관리자는 DCL을 사용하여 사용자별로 필요한 최소한의 권한만을 부여함으로써, <br/>
데이터베이스 시스템의 안전을 보장할 수 있습니다.<br/>

## SELECT 쿼리 실행 순서

![쿼리실행순서](https://blog.kakaocdn.net/dn/dOq9i4/btsDQ0mOa2U/Ok1Xmn3BXiQ6ELcFzr7HLk/img.png)

1. **FROM (+ Join)** </br>
   쿼리의 첫번째 실행 순서는 FROM절입니다. FROM 절에서는 조회할 테이블을 지정합니다. </br>
   이후 Join을 실행하여 하나의 가상 테이블로 결합합니다. </br>
   </br>
2. **WHERE**</br>
   WHERE 절에서는 테이블에서 조건에 맞는 데이터를 필터링합니다.</br>
   </br>
3. **GROUP BY**</br>
   GROUP BY 절에서는 선택한 칼럼을 기준으로 조회한 레코드 목록을 그룹핑합니다.</br>
   </br>
4. **HAVING**</br>
   HAVING 절은 그룹핑 후에 각 그룹에 사용되는 조건 절이다. 쉽게 말해 그룹을 필터링한다고 생각하면 됩니다.</br>
   HAVING 절의 조건을 WHERE 절에도 사용할 수 있는 경우라면 WHERE절에 사용하는 것이 바람직합니다.
   HAVING 절은 각 그룹에 조건을 걸기 때문에 퍼포먼스가 떨어지게 됩니다.</br>
   예를 들어 MONEY > 10000은 모든 레코드에 MONEY가 10000이 넘어야 한다는 조건입니다.</br>
   이는 각 그룹에 따로 거는 것보다는 WHERE절로 한번에 거는 것이 좋습니다.</br>
   </br>
5. **SELECT**</br>
   SELECT 절은 기본적인 조회 구문으로 여러 조건들을 처리한 후 남은 데이터에서 어떤 열을 출력해줄지 선택합니다.</br>
   </br>
6. **ORDER BY**</br>
   어떤 열까지 출력할지 정했다면 행의 순서를 어떻게 보여줄지 정렬 해주는 절이 ORDER BY입니다.</br>
   ASC(오름차순), DESC(내림차순)입니다. </br>
   </br>
7. **LIMIT**</br>
   LIMIT 절은 결과 중 몇개의 행을 보여줄 지 선택합니다.</br>

![쿼리실행순서시각화](https://blog.kakaocdn.net/dn/tm2XI/btsDJXdXdqS/o6xVybnz0zFzFyaSqMj2a1/img.png)
