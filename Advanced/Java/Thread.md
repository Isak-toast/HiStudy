# 쓰레드
> 작성자 : [장성근](https://github.com/heoap9)

<details>
<summary>Table of Contents</summary>

- [Thread 개념 및 자바의 Thread 실행](#thread)
- [함수형 언어와 Thread 그리고 Runnable](#thread)
- [가상쓰레드](#virtual-thread-동작원리)
- [정리](#정리)
</details>



# Thread

> 메모리를 할당받아 실행 중인 프로그램을 프로세스라고 부르고
프로세스 내에서 복수의 Thread를 사용하여 실행흐름을 한 갈래가 아닌 다수의 흐름을 가지게 한다
두개 이상의 스레드를 가지는 프로세스를 멀티스레드 프로세스라고 부른다
> 

Thread 클래스를 확장하여 run()메서드만 오버라이드를 진행해주게 된다면

쓰레드 내에서 동작할 환경을 구성하여 흐름을 가져갈 수 있다

```java
public class Main extends Thread{
    private static final Random random = new Random();
    
    @Override
    public void run(){
        String threadname =Thread.currentThread().getName();
        System.out.println("-"+threadname+"has been started");
        int delay =1000 + random.nextInt(4000);
        try{
            Thread.sleep(delay);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("- "+threadname+"has been ended"+delay+"ms");
    }
```

위 코드는 Thread 클래스를 상속받아 별도로 실행흐름을 가져가게 할 동작흐름을 보여주고 있다

즉 run()이라는 메서드만 오버라이드 해주어 객체 생성 시 쓰레드의 흐름이 새로 추가됨과 같습니다
![Untitled.png](Thread%2FUntitled.png)

스레드는 위와 같이 상태를 변경할 수 있으며

스레드를 상속받은 클래스의 객체를 실행 후 Start()메서드를 실행하면 main 쓰레드가 아닌 별도의 쓰레드에서 명령이 실행할 수 있음을 확인할 수 있습니다

```java
@Test
void threadStart() {
    Thread thread = new MyThread();

    thread.start();
    System.out.println("Hello: " + Thread.currentThread().getName());
}

static class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName());
    }
}

// 출력 결과
// Hello: main
// Thread: Thread-2
```

여기서 핵심은 run을 직접호출하는것이 아니라 Start를 호출하는것을 중요하게 생각해야 합니다

run을 직접 호출하는것은 메인쓰레드에서 객체의 메서드를 호출하는것에 불과합니다

이를 쓰레드 개념으로 실행하려는것은 Thread안의 기능을 중요하게 봐야 합니다

```java
public synchronized void start() {
    if (threadStatus != 0)
        throw new IllegalThreadStateException();

    group.add(this);

    boolean started = false;
    try {
        start0();
        started = true;
    } finally {
        try {
            if (!started) {
                group.threadStartFailed(this);
            }
        } catch (Throwable ignore) {
        
        }
    }
}
```

자바의 쓰레드 Start는 크게 다음과 같은 과정으로 진행됩니다

1.쓰레드가 실행 가능한지 검사

2.쓰레드를 쓰레드 그룹에 추가

3.쓰레드를 JVM 이 실행시킴

쓰레드가 실행 가능한지 검사합니다

쓰레드는 New,Runnable,Wating,TimedWating,Terminate 총 5가지 상태를 지키며

Thread 클래스의 Start메서드는 가장 처음에는 해당 쓰레드가 실행가능한 상태인지 확인합니다

그리고 만약 쓰레드가 New(0)상태가 아니라면 illegalThreadStateException 예외를 발생시킵니다

![Untitled 1.png](Thread%2FUntitled%201.png)
그 다음 쓰레드 그룹에 해당 쓰레드를 추가시켜 관리합니다

여기서 주목해야 할 점은 바로 blocked 키워드인데 이 blocked는 synchronized의 자원소모가 매우 큰 문제가 있습니다

(os커널 접근 및 자원상태관리)

<aside>
💡 쓰레드 그룹이란?

</aside>

서로 관련있는 쓰레드를 하나의 그룹으로 묶어 다루기 위한 장치인데 자바에서는 ThreadGroup 클래스를 제공하여

이 그룹에 해당 쓰레드를 추가하면 그룹에 실행 준비된 쓰레드가 있음을 알려주고 관련 작업들이 내부적으로 진행됩니다

그리고 Start0메서드를 호출하여 jvm이 내부적으로 run을 실행함을 나타냅니다

그리고 쓰레드의 상태 역시 Runable상태로 바뀌게 됩니다

```java
private native void start0();
```

그래서 start는 여러 번 호출하는 것이 불가능하고 1번만 가능합니다

Runnable

앞서 살펴보았던 Thread 클래스는 반드시 run메서드를 상속받아 구현해야 했는데

이 Thread클래스가 Runable를 구현하고 있기 때문입니다!

```java
public class Thread implements Runnable {
    ...
}
```

```java
@Test
void runnable() {
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    };

    Thread thread = new Thread(runnable);
    thread.start();
    System.out.println("Hello: " + Thread.currentThread().getName());
}
```

즉 위와 같이 해당 쓰레드의 Runable 으로 코드의 구현이 진행됨을 볼 수 있습니다

![Untitled 2.png](Thread%2FUntitled%202.png)
Thread와 Runnable의 비교!

Runnable은 익명 객체 및 람다로 사용이 가능하지만 Thread는 별도의 클래스를 만들어야 하는점에 있어 번거롭다

그리고 다중상속(다이아몬드 문제)가 불가능하므로 Thread를 상속받는 경우 다른클래스를 상속받을 수 없어 구현의 제한이 있을 수 있으며

결국 Thread클래스의 구현된 코드들에 의해 더 많은 자원을 필요로 진행하기에 Runnable 을 함수형태로 선언하여 실행하는 형태로 진행하게 된다!

이 기본적인 Thread와 Runnable의 단점 및 한계는 명확합니다

지나치게 저수준의 api에 의존하고 있는 메서드 이며

값의 반환이 불가능하며

그리고 매번 쓰레드 생성과 종료하는 오버헤드가 발생하는 문제가 있습니다

그리고 쓰레드의 관리가 너무 어렵다는 문제가 있습니다(JVM의 한계)

이러한 쓰레드의 사용방법을 자바는 꾸준히 발전하고 있으며 Executor등 여러가지 api를 제공하고 있습니다!

# Synchronized

```java
package thread;

class PrintClass{
    public void print(int n){
        for(int i = 1; i <= 5; i++){
            System.out.println("Current Thread : " + Thread.currentThread().getName() + " value : " + n * i);
            try{
                Thread.sleep(3000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class TestThread extends Thread{
    PrintClass pc;
    int num;

    public TestThread(PrintClass pc, int num){
        this.pc = pc;
        this.num = num;
    }

    @Override
    public void run() {
        pc.print(this.num);
    }
}

public class SynchronizedTest {
    public static void main(String[] args) {
        PrintClass pc = new PrintClass();

        TestThread th1 = new TestThread(pc, 5);
        TestThread th2 = new TestThread(pc, 100);

        th1.start();
        th2.start();

    }    
}
// 출력
/*
Current Thread : Thread-0 value : 5
Current Thread : Thread-1 value : 100
Current Thread : Thread-1 value : 200
Current Thread : Thread-0 value : 10
Current Thread : Thread-1 value : 300
Current Thread : Thread-0 value : 15
Current Thread : Thread-0 value : 20
Current Thread : Thread-1 value : 400
Current Thread : Thread-0 value : 25
Current Thread : Thread-1 value : 500
*/
```

위 코드는 동기화를 진행하지 않았을 경우 각각의 스레드가 동시에 돌아가며 시스템의 동작흐름에 따라 출력값이 나오고 있다

```java
package thread;

class PrintClass{
    synchronized public void print(int n){ //동기화!
        for(int i = 1; i <= 5; i++){
            System.out.println("Current Thread : " + Thread.currentThread().getName() + " value : " + n * i);
            try{
                Thread.sleep(3000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class TestThread extends Thread{
    PrintClass pc;
    int num;

    public TestThread(PrintClass pc, int num){
        this.pc = pc;
        this.num = num;
    }

    @Override
    public void run() {
        pc.print(this.num);
    }
}

public class SynchronizedTest {
    public static void main(String[] args) {
        PrintClass pc = new PrintClass();

        TestThread th1 = new TestThread(pc, 5);
        TestThread th2 = new TestThread(pc, 100);

        th1.start();
        th2.start();

    }    
}
//출력
/*
Current Thread : Thread-0 value : 5
Current Thread : Thread-0 value : 10
Current Thread : Thread-0 value : 15
Current Thread : Thread-0 value : 20
Current Thread : Thread-0 value : 25
Current Thread : Thread-1 value : 100
Current Thread : Thread-1 value : 200
Current Thread : Thread-1 value : 300
Current Thread : Thread-1 value : 400
Current Thread : Thread-1 value : 500
*/
```

메서드 앞에 동기화를 진행한 결과 th1.의 실행이 끝난 뒤 th2의 실행을 진행하고 있으며

이것이 바로 동기화의 핵심이 된다!

```java
package thread;

class PrintClass{
    synchronized public void print(int n){
        for(int i = 1; i <= 5; i++){
            System.out.println("Current Thread : " + Thread.currentThread().getName() + " value : " + n * i);
            try{
                Thread.sleep(3000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class TestThread extends Thread{
    PrintClass pc;
    int num;

    public TestThread(PrintClass pc, int num){
        this.pc = pc;
        this.num = num;
    }

    @Override
    public void run() {
        pc.print(this.num);
    }
}

public class SynchronizedTest {
    public static void main(String[] args) {
        PrintClass pc = new PrintClass();

        TestThread th1 = new TestThread(pc, 5);
        TestThread th2 = new TestThread(pc, 100);
        TestThread th3 = new TestThread(new PrintClass(), 1);

        th1.start();
        th2.start();
        th3.start();

    }    
}
//출력
/*
Current Thread : Thread-0 value : 5
Current Thread : Thread-2 value : 1
Current Thread : Thread-0 value : 10
Current Thread : Thread-2 value : 2
Current Thread : Thread-0 value : 15
Current Thread : Thread-2 value : 3
Current Thread : Thread-0 value : 20
Current Thread : Thread-2 value : 4
Current Thread : Thread-0 value : 25
Current Thread : Thread-2 value : 5
Current Thread : Thread-1 value : 100
Current Thread : Thread-1 value : 200
Current Thread : Thread-1 value : 300
Current Thread : Thread-1 value : 400
Current Thread : Thread-1 value : 500
*/
```

근데 만약 새로운 class를 생성하여 동기화를 관리한다면?

세번째 스레드는 결국 별개의 동기화 블럭에서 관리되고있는 객체가 되며 이 관리가 어려워지고 있다!

즉 이러한 문제를 해결하기 위해서는 다른 동기화 블럭을 제공하여 객체들을 따로 관리해주어야 하는 문제를 가지고 있으니

항상 쓰레드와 동기화 문제는 조심하면서 접근해야 합니다

즉 동기화의 상태관리 및 분기처리는 고려해야 될 대상이 많으며

특히 이 Syncronized 키워드 자체는 자원소모가 큰편이다

```java
import java.util.concurrent.TimeUnit;

public class Main {
    private static boolean stopRequested;
    public static void main(String[] args) throws InterruptedException{
        System.out.println("hello world!");
        Thread backgroundThread = new Thread(() -> {
            int i=0;
            while(!stopRequested){
                i++;
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
```

위 코드는 동작이 중지되지 않는 코드이다

Main쓰레드와 개발자가 직접 만들어낸 다른 쓰레드의 흐름이

위 StopRequested코드에서 별개의 자원으로 관리되고 있다는것이 문제가되고 있어

위 코드를 동작하게되면 무한정으로 동작하게 됩니다

<aside>
😡 결국 정신 꽉붙잡고 쓰레드를 관리해줘야된다는것!

</aside>

![Untitled 3.png](Thread%2FUntitled%203.png)

Cpu의 캐시메모리 구조

Os상에서는 이 쓰레드를 직접 관리하여 매핑한 뒤 JVM으로 전달해주는 역할을 해주고있었는데

main쓰레드와 생성한 쓰레드는 각자 다른 캐시에 매핑되어있기 때문입니다

즉 쓰레드라는 특성 상 이 동기화 키워드는 컴퓨터 구조와 밀접하게 관련이 있기 때문에 상태관리를 중요하게 생각해야 합니다

즉 캐시된 공유변수(StopRequested)를 동기화 해주지 않았기 때문에 다른 스레드에서 실질적으로 메모리에 값을 true로 변경하였더라도

반복문에 쓰이고 있는 cpu의 쓰레드에서는 l1,l2와 같은곳에서 캐시 된 값을 참고하고 있어 문제가 된것이었습니다

즉 위와 같은 문제를 접근할때에는 동기화를 진행하는데 있어 중요하게 생각해야 하는 부분이며

```java
private static volatile boolean stopRequested;
```

위 volatile 키워드를 사용하여 캐시가 아닌 메모리로 값 참조를 통해 해결할 수도 있습니다

<aside>
💡 자바의 쓰레드는 관리가 어렵다 (JVM → OS간의 통신)

</aside>

![Untitled 4.png](Thread%2FUntitled%204.png)
이처럼 기존 JAVA의 쓰레드 모델은 Native Thread로 java의 유저 쓰레드를 만들게 되면 

JNI(인터페이스)를 통해 커널영역을 호출하여 os에 직접 커널 스레드를 생성하고 매핑하여 작업을 진행하고 있습니다

이러한 가는 통로와 길이 인터페이스를 거쳐가야 하는 문제 및 서로 os간의 호출 및 통신이 지속적으로 이루어져 있는데

과연 이 밑의 저수준 인터페이스(C언어)와 JVM의 매핑하는 문제가 너무 불필요하게 연결되고 있는 문제가 있다는것이다!

이를 해결하기 위해 개발자들은 Executor를 사용하는 등 여러가지 연구를 진행하였지만

요즘 트렌드는 바로 가상쓰레드로 JVM안에서 쓰레드를 관리하는 형식으로 사용되게 하였다!

![Untitled 5.png](Thread%2FUntitled%205.png)
Java의 쓰레드는 I/O,interupt,sleep 와 같은 상황에 blocking/wating 상태가 되는데

이때 다른 스레드가 커널 쓰레드를 점유하여 작업을 수행하는것을 “**컨텍스트 스위치**”라고 부릅니다

이러한 쓰레드 모델은 기존 프로세서 모델을 잘게 쪼개 프로세스 내의 공통된 부분은 공유하며

작은 여러 실행단위를 번갈아 가며 수행할 수 있게 만들어져 있습니다

이러한 쓰레드는 프로세스의 공통영역을 제외하고 만들어져있기 때문에 프로세스에 비해 작아 생성비용이 적으며

컨텍스트 스위칭 비용이 저렴했기에 주목받고 있었습니다

하지만 대규모 웹서비스에서 요청량이 매우 많은 서버에서는 이러한 쓰레드 마저도 엄청나게 많은 스레드 수를 요구하게 되며

스레드의 사이즈가 프로세서에 비해 작다고 해도 스레드 1개당 1mb사이즈라고 가정하면 4GB메모리 환경에서 많아봐야 4천개밖에 스레드를 가질수있습니다

이처럼 메모리가 제한된 환경에서는 생성할 수 있는 스레드의 수에 한계가 있으며

스레드가 많아질수록 이 컨텍스트 스위칭의 비용이 당연하게 많아질 수 밖에 없다는 점이 있었습니다

이러한 한계를 극복하기 위해 컨텍스트 스위칭 비용을 줄여야 했는데

이를 위해 나타난 스레드 모델이 경량 스레드 모델인 가상쓰레드 입니다!

# 가상쓰레드 (JDK21 버전부터 진행)
![Untitled 6.png](Thread%2FUntitled%206.png)
Virtual Thread는 기존의 Java Thread모델과는 달리 플랫폼 스레드와 가상 쓰레드로 나뉘어져 있습니다

쓰레드 위에서 여러 Virtual Thread 가 번갈가며 실행되는 형태로 동작되며 마치 커널 쓰레드와 유저 쓰레드가 매핑되는 형태랑 비슷합니다

여기서 가장 큰 특징은 Virtual Thread는 컨텍스트 스위칭 비용이 저렴하다는 것입니다!

![Untitled 7.png](Thread%2FUntitled%207.png)
Thread는 기본적으로 최대 2mb의 Stack 메모리 사이즈를 할당하여 스위칭을 진행하기 때문에

메모리의 이동량이 크고 생성을 하는 경우 커널과 통신하여 스케줄링 해야 하므로 시스템 콜을 이용하기 때문에 생성하는 비용이 적지 않습니다

하지만 앞서 설명한 Virtual Thread는 JVM에 의해 실행되어 생성되기 때문에 시스템 콜과 같은 커널 영역의 호출이나 스위칭이 적고

메모리의 크기가 일반 쓰레드에 비해 1%밖에 되지 않는다는 컨텍스트 스위칭 비용이 비약적으로 적다는 장점을 가지고 있습니다

우선 앞서 나온 ForkJoinPool을 사용하게 되는데 스케쥴러는 platform Thread pool을 관리하고

Virtual Thread의 작업분배를 진행합니다!
![Untitled 8.png](Thread%2FUntitled%208.png)
디버거를 통해 분석해보면

VirtualThread는 CarrierThread를 가지고 있으며

실제 작업을 수행시키는 platformThread를 의미하며 CarrierThread는 workQueue를 가지고 있습니다

scheduler라는 ForkJoinPool을 가지고 있으며 carrier thread의 pool역할을 하며 가상스레드의 작업 스케쥴링을 담당합니다

runCoutinuation이라는 virtual thread의 실제 작업내용(Runnable)을 가지고 있습니다

### Virtual Thread 동작원리
![Untitled 9.png](Thread%2FUntitled%209.png)

1. 실행될 virtual thread의 작업인 runContinuation을 carrier thread의 workQueue에 push 합니다.
2. Work queue에 있는 runContinuation들은 forkJoinPool에 의해 work stealing 방식으로 carrier thread에 의해 처리됩니다.
3. 처리되던 runContinuation들은 I/O, Sleep으로 인한 interrupt나 작업 완료 시, work queue에서 pop되어 park과정에 의해 다시 힙 메모리로 되돌아갑니다.

기존의 Thread모델에서 virtual thread의 park,unpark 동작을 통해 virtual thread의 컨텍스트 스위칭 하는 형태로 동작하며

기존의 Thread는 이 인터페이스 속에서 이 작업을 진행하였지만 가상쓰레드는 JVM프로세서 안에서 작업이 이루어지고 있는것을 알 수 있습니다!

이처럼 가상 쓰레드 도입을 통해 여러가지 문제점을 해결하기 위한 접목요소로 가상쓰레드를 도입하였지만

너무 최신버전의 기능이기때문에 알고만 있는것이 좋다고 생각합니다!

# 정리

<aside>
💡 사람도 멀티테스킹을 잘 못하듯이
결국 기계도 멀티테스킹을 하려면 고려해야될것이 많다는것!

</aside>

- 쓰레드는 프로그램의 동작을 병렬처리 하기 위한 목적으로 사용된다
- 자바는 가상머신이라는 특성을 가지기 때문에 시스템 Os의 접근하는 인터페이스와 시스템의 구조를 고려해야 한다
- 결국 JVM의 구조상의 메모리와 캐시를 사용하는것이 아닌 시스템상에 접근하는 캐시메모리에 탑재되어 관리되기 때문에
공유되는 값이나 동작흐름을 관리하는데 있어서는 volataile Syncronized 키워드를 사용하면서도 유의하여 관리해야 한다

- 본 글에서는 다루지 않지만 데드락 이슈도 존재하기 때문에 여러 접근점을 조심해야 됩니다!

![Untitled 10.png](Thread%2FUntitled%2010.png)
번외로 여러 문서와 인터뷰를 확인해보니 쓰레드 자체의 문제가 너무 많다는것을 확인했으며

이 병렬처리 기법에 대해 많은 개발자들은 고찰을 진행하고 있었던 것이었다!

특히 전 주간에서 배우게 된 함수형 프로그래밍의 불변성의 특성(원본의 값을 복제하거나 변경시키지 않음)을 활용하여 결국 StreamAPI가 탄생할수 밖에 없는 이유가 있었으며
대량으로 데이터를 처리하는 경우 이러한 고민을 할수밖에 없는 현업자들의 고충을 조금이나마 이해할 수 있는 시간이 되었습니다!

[스레드(Java 플랫폼 SE 8) (oracle.com)](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)

[Java의 미래, Virtual Thread | 우아한형제들 기술블로그 (woowahan.com)](https://techblog.woowahan.com/15398/)

[자바 동기화, 어설프게 아는 사람이 더 무섭다(java synchronized에 대한 착각, thread-safe) (tistory.com)](https://jeong-pro.tistory.com/227)