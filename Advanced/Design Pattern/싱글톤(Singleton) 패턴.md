# 싱글톤(Singleton) 패턴이란 무엇이며, 언제 사용하는 것이 적합한가요?

> 작성자 : [정혜연](https://github.com/jeonglever)

<details>
<summary>Table of Contents</summary>

- [싱글톤(Singleton) 패턴이란](<#싱글톤(Singleton)-패턴이란>)
  - [싱글톤 패턴을 사용하는 이유](#싱글톤-패턴-사용-이유)
- [싱글톤 패턴 예시](#싱글톤-패턴-예시)
  - [게으른 초기화(lazy initialization)](<#게으른-초기화(lazy-initialization)>)
  - [이른 초기화(early initialization)](<#이른-초기화(early-initialization)>)
  - [더블 체킹 락(double-checked locking)](<#더블-체킹-락(double-checked-locking)>)
- [결론](#결론)
  </details>

---

## 싱글톤(Singleton) 패턴이란

"**싱글톤(Singleton) 패턴**"이란, 객체의 인스턴스가 오직 하나만 생성되도록 보장하는 디자인 패턴이다. <br/>
싱글톤 패턴은 객체지향 프로그래밍에서 중요하게 여겨지는 디자인 패턴 중 하나인데, <br/>
객체지향 프로그래밍 특징 중 '캡슐화'와 '추상화'를 적극적으로 활용한 패턴이라고 볼 수 있다. <br/>

### 싱글톤 패턴을 사용하는 이유

![예시](https://injae-kim.github.io/assets/Dev/2020-08-06-singleton-pattern-usage/%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C1.JPG)

싱글톤의 핵심 개념은 **전체 시스템에서 단 하나의 인스턴스만 존재해야 한다**는 점이다. <br/>
그렇기 때문에 싱클톤 패턴은 클래스의 인스턴스가 하나만 생성되는 대신, 시스템 어디에서든지 그 인스턴스에 접근할 수 있도록 보장하도록 설계한다. <br/>
이 패턴은 특정 클래스에 대한 인스턴스 생성을 제한하고, 전역적으로 접근 가능한 단일 인스턴스를 제공한다.

## 싱글톤 패턴 예시

싱글톤 패턴에는 여러가지 예시가 있다. 다음은 가장 기본적인 예시인 게으른 게으른 초기화(lazy initialization)이다.

### 게으른 초기화(lazy initialization)

```java

public class Printer {
    // 자기 자신의 유일한 객체를 static 변수로 가진다.
    private static Printer instance;

    // 생성자의 접근제한자를 private으로 선언하여, 외부에서 new 키워드를 통한 객체 생성을 막는다.
    private Printer() {}

    // 객체에 접근할 수 있는 유일한 방법을 메서드를 통해 제공한다.
    public static Printer getInstance() {
        if (instance == null) {
            instance = new Printer(); // 객체가 아직 생성되지 않았다면 새로 생성하고, 이미 있다면 기존 객체를 반환
        }
        return instance;
    }

    // 문서를 인쇄하는 메서드
    public void printDocument(String document) {
        System.out.println(document);
    }


}

```

간단한 사용 예시 코드

```java

public class Main {
    public static void main(String[] args) {
        // Printer 싱글톤 인스턴스를 가져온다.
        Printer printer = Printer.getInstance();

        // Printer 인스턴스를 사용하는 코드...
        printer.printDocument("Hello, World!");
    }
}

```

이 방식은 getInstance() 메소드가 호출될 때까지 객체를 생성하지 않는다. <br/>
기존에 생성된 값이 존재한다면 생성된 인스턴스를 리턴하는 형태로 프로그램 전반에 걸쳐 하나의 인스턴스를 유지한다. <br/>
하지만, 멀티스레드 환경에서는 이 방식이 안전하지 않을 수 있다. <br/>
여러 스레드가 동시에 getInstance()를 호출할 때, 여러 인스턴스가 생성될 가능성이 있기 때문. <br/>
<br/>
멀티스레드 환경에서 안전한 싱글톤 패턴을 구현하기 위해서는 다음과 같은 방식들이 있다.

### 이른 초기화(early initialization)

```java

public class Printer {
    // 생성 시점에 인스턴스를 생성한다.
    // JVM이 클래스를 로딩하고 초기화하는 과정에서 단 한 번만 인스턴스가 생성된다.
    private static final Printer instance = new Printer();

    private Printer() {}

    public static Printer getInstance() {
        return instance;
    }
}

```

이른 초기화 방식은 **클래스가 로딩될 때 인스턴스가 생성**되므로, 멀티스레드 환경에서도 안전하게 사용할 수 있다. <br/>
이 방식은 인스턴스가 프로그램 실행 동안 항상 필요하다는 것이 확실한 경우에 적합한데, <br/>
인스턴스를 반드시 생성하기 때문에, 실제로 사용되지 않을 수 있는 경우 리소스 낭비가 되기 때문. <br/>
JVM이 클래스 로딩을 처리하는 방식 덕분에, 추가적인 동기화 없이도 스레드 안전성을 보장한다. <br/>

### 더블 체킹 락(double-checked locking)

```java

public class Printer {
    // volatile 키워드를 사용하여, instance 변수가 여러 스레드에 의해 동시에 사용될 때 문제가 없도록 한다.
    private static volatile Printer instance;

    private Printer() {}

    public static Printer getInstance() {
        if (instance == null) {
            // 클래스 레벨에서 동기화를 진행한다. 첫 번째 검사 후, 인스턴스가 없을 때만 동기화를 진행한다.
            // 멀티 스레드 환경에서 여러 스레드가 동시에 접근하려고 할떄 한번에 하나의 스레드만 사용하도록 Printer.class를 락 건 것.
            synchronized (Printer.class) {
                // 동기화 블록 안에서 한 번 더 인스턴스가 생성되지 않았는지 확인한다.
                // 이 검사로 인스턴스가 정말로 필요할때 한 번만 생성되고 마는 것.
                if (instance == null) {
                    instance = new Printer();
                }
            }
        }
        return instance;
    }
}

```

*volatile*은 자바에서 변수를 메인 메모리에 저장하겠다는 것을 의미한다. 이 변수의 읽기와 쓰기가 항상 메인 메모리를 통해 이루어지도록 하는 것이다. <br/>
이는 변수의 가시성(visibility)을 보장하고, **여러 스레드가 동일한 변수에 동시에 접근할 때 하나의 스레드에 의해 변경된 값이 다른 스레드에 즉각적으로 반영**되도록 한다. <br/>
따라서, instance 변수에 대한 변경 사항이 모든 스레드에게 올바르게 보이도록 하여, 싱글톤 인스턴스가 중복으로 생성되는 문제를 방지하는 것. <br/>
더블 체킹 락 패턴에서는 volatile 키워드 없이는 올바르게 작동하지 않을 수 있는데, <br/>
volatile 없이는 컴파일러 최적화로 인해 다른 스레드가 아직 완전히 생성되지 않은 객체를 보고 사용할 수 있기 때문이다. <br/>

## 결론

싱글톤은 프로그램 전체에서 하나의 객체만을 공통으로 사용하고 있다. 즉, 전역 상태로 관리된다.
이는 각 객체간의 결합도가 높아지고 변경에 유연하게 대처할 수 없어 유지보수가 어려워지는 측면이 있다.
싱글톤 객체가 변경되면 이를 참조하고 있는 모든 값들이 변경되어야 하기 때문이다. <br/>

멀티쓰레드 환경에서 대처가 어느정도 가능하지만 고려해야 할 요소가 많아 사용이 어렵고, 동기화로 인해 성능이 저하될 수 있다.
물론 프로그램 전반에 걸쳐서 필요한 부분에만 사용한다면 장점이 있다. 하지만 그 포인트를 잡기가 어렵다. <br/>

하지만 싱글톤 패턴은 동일한 리소스나 서비스에 대해 여러 인스턴스를 생성하는 것보다 메모리 사용을 줄여주며, 애플리케이션의 전반적인 메모리 효율성을 향상시킨다.<br/>
더불어 공유 리소스나 서비스에 대한 중앙 집중식 관리를 가능하게 한다. 데이터베이스 연결이나 파일 시스템, 로그 관리 등의 공유 리소스에 대해 단일 접근 지점을 제공함으로써, 이러한 리소스의 사용을 효율적으로 관리할 수 있다.<br/>

결국 적절한 형태로 싱글톤을 활용하면 좋지만 남용하게 될 여지가 많기 때문에 설계 단계에서 잘 고민하고 적용할 것! <br/>
