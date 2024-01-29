# DTO를 사용하는 이유

> 작성자 : [정혜연](https://github.com/Jeonglever),

<details>
<summary>Table of Contents</summary>

- [DTO(Data Transfer Object)](#DTO-Data-Transfer-Object)
- [DTO의 특징](#DTO의-특징)
- [DTO 선언 예제](#DTO-선언-예제)
- [DTO와 ENTITY의 차이점](#dto와-entity의-차이점)

</details>

---

## DTO(Data Transfer Object)

DTO(Data Transfer Object)는 데이터를 전달하기 위해 사용되는 객체 지향 프로그래밍의 디자인 패턴 중 하나이다.</br>
DTO는 일반적으로 비즈니스 로직을 포함하지 않고, **순수하게 데이터 전달을 목적**으로 한다.</br> 그렇기에 주로 계층간 데이터 교환을 위해 사용되며, 특히 소프트웨어의 다양한 계층(프레젠테이션 ↔ 비즈니스 로직 ↔ 데이터 액세스) 간에 데이터를 전달하는 데 적합하다.

### DTO의 특징

#### 1. 데이터 캡슐화

DTO는 관련 데이터를 하나의 객체로 묶어서 **캡슐화**한다. 이는 여러 데이터 필드를 하나의 객체로 묶어 전달함으로써 메소드 호출의 간소화와 코드의 명료성을 증가시킨다.

#### 2. 네트워크 최적화

DTO는 네트워크를 통한 데이터 전송에 효율적이다. 예를 들어, 클라이언트-서버 간의 통신에서 필요한 데이터만을 담아 **한 번의 호출로 필요한 모든 데이터를 전달**할 수 있다.

#### 3. 분리 및 추상화

DTO를 사용하면 비즈니스 로직이나 데이터베이스 구조와 같은 내부 구현의 복잡성을 프레젠테이션 계층으로부터 분리할 수 있다. 이를 통해 시스템의 각 계층은 독립적으로 유지될 수 있고, **유지보수와 확장성이 향상**된다.

#### 4. 데이터 무결성

DTO는 **데이터 무결성을 보장**하는 데에도 도움이 됩니다. 데이터가 전송 과정에서 변경되지 않도록 보호하며, 오직 필요한 데이터만을 포함시켜 안전한 데이터 교환을 가능하게 합니다.

#### 5. 재사용성

잘 설계된 DTO는 여러 다른 컨텍스트에서 재사용될 수 있다. 이는 중복 코드의 감소를 의미하며, **소프트웨어의 모듈성을 향상**시킨다.

#### 6. 비즈니스 로직과 데이터 액세스 로직의 부재

DTO는 단순히 데이터를 전달하는 역할에 집중하기 때문에, 복잡한 비즈니스 로직이나 DB와의 상호작용(데이터 액세스 로직)을 포함하지 않는다.

## DTO 선언 예제

DTO는 데이터 필드(변수)와 이 필드에 접근하기 위한 게터(getter)와 세터(setter) 메소드로 구성된다.

```java
public class UserDTO {
    private String name;
    private String email;
    private int age;

    // 기본 생성자
    public UserDTO() {
    }

    // 매개변수가 있는 생성자
    public UserDTO(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // Getter와 Setter 메소드
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // toString 메소드 (디버깅 목적)
    @Override
    public String toString() {
        return "UserDTO{" +
               "name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", age=" + age +
               '}';
    }
}

```

## DTO와 ENTITY의 차이점

DTO와 ENTITY는 일견 비슷해 보일수 있겠지만 서로 다른 목적과 특성을 가지고 있다.</br>
그렇기에 DTO와 ENTITY를 분리하여 사용하는 것은 소프트웨어 설계에서 중요한 패턴 중 하나이다.

### DTO

1. 데이터를 전달하는 데 사용될 뿐 DB의 구조를 모른다.
2. 비즈니스 로직이나 데이터베이스 로직을 포함하지 않는다.
3. 주로 View와 Controller 계층에서 데이터를 주고받을 때 활용된다.
4. 요청 또는 응답의 수명 동안만 존재한다.

### ENTITY

1. DB와의 연동을 고려하여 설계되며, 키, 관계, 제약조건 등을 반영한다.
2. DB의 테이블과 객체 간의 매핑을 정의한다.
3. 데이터의 영속성과 일관성을 관리한다.
4. 애플리케이션의 전체 수명 주기 동안 DB와 상호 작용한다.


![참고 이미지](https://user-images.githubusercontent.com/33862991/116250387-a46ec800-a7a8-11eb-8368-8e2df28225fa.png)