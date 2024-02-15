# PHP에 관하여

> 작성자 : [신우철](https://github.com/Jeremy-fe)

<details>
<summary>목차</summary>

- [1. PHP란?](#1.-PHP란?)
- [2. PHP의 주요 특징](#2.-PHP의-주요-특징)
- [3. 간단한 PHP 코드 예제](#3.-간단한-PHP-코드-예제)


</details>

---

## 1. PHP란?
- 하이퍼텍스트 프리프로세서(PHP): PHP는 주로 HTML 문서 안에 삽입하여 웹 페이지를 동적으로 생성하는 데 사용됩니다.

- 서버 측 언어: 클라이언트의 요청을 받아 서버에서 처리하고 결과를 클라이언트에게 전송하는 역할을 합니다.

- 무료 및 오픈 소스: PHP는 무료로 사용할 수 있으며, 많은 개발자들이 기여하여 계속 발전하고 있는 오픈 소스 언어입니다.

## 2. PHP의 주요 특징
- 간단하고 배우기 쉬운 문법: C, Java와 비슷한 문법을 가지고 있어 새로운 개발자들이 빠르게 익힐 수 있습니다.

- 동적 웹 페이지 생성: PHP는 HTML 코드 안에 삽입하여 동적으로 웹 페이지를 생성할 수 있습니다.

- 데이터베이스 연동: 주로 MySQL과 같은 데이터베이스와 연동하여 데이터를 처리하고 관리할 수 있습니다.

- 강력한 기능: 다양한 라이브러리와 확장 기능을 지원하여 다양한 작업을 수행할 수 있습니다.


## 3. 간단한 PHP 코드 예제
### 변수 및 출력
```php
<?php
  $message = "Hello, PHP!";
  echo $message;
?>
```
### 조건문과 반복문
```php
<?php
  $number = 10;
  
  if ($number > 0) {
    echo "Positive";
  } else {
    echo "Negative";
  }

  for ($i = 0; $i < 5; $i++) {
    echo $i;
  }
?>
```
### 함수 정의와 호출
```php
<?php
  function greet($name) {
    echo "Hello, $name!";
  }

  greet("John");
?>
```
### HTML 태그 출력
```php
<?php
  $name = "John";
  echo "<p>Welcome, $name!</p>";
?>
```

### 1. cURL을 사용한 GET 요청:
```php
<?php
// cURL 초기화
$ch = curl_init();

// 설정 옵션 지정
curl_setopt($ch, CURLOPT_URL, 'https://example.com/api/data'); // 요청할 URL
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); // 결과를 문자열로 반환

// 요청 실행 및 응답 저장
$response = curl_exec($ch);

// cURL 세션 종료
curl_close($ch);

// 응답 출력
echo $response;
?>

```