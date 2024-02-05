# 제네릭이 무엇인가요?

> 작성자 : [장성근](https://github.com/heoap9)

<details>
<summary>Table of Contents</summary>

- [스택이란?](#Stack)
- [스택의 동작방식](#Stack 동작원리)
    - [스택의 사용 예](#Stack 사용 예)
- [큐란?](#Queue)
- [큐의 구현](#Queue 구현)
- [Queue 활용사례](#queue-활용사례)

</details>

---

## Stack
![stack.png](mdimageresource%2Fstack.png)  
"**Stack**"은 선형 자료구조 중 하나이며  
자료구조를 할당하고 데이터를 넣기 시작하면 제일 먼저 들어간 데이터가  
가장 나중에 나오는 구조를 가진 자료구조이다!
LIFO(Last in first out) 를 명심하자  

## 스택의 동작방식  
![stacktrace1.png](mdimageresource%2Fstacktrace1.png)  
스택 자료구조의 기본 작동원리  
- 먼저 자료구조를 정의하고 실행하게되면 끝이 정해져 있지 않은 자료구조를 선언하게 된다
  - push는 데이터를 넣는 연산
  - pop은 stack에 들어간 데이터를 빼는 연산
  - 기본적인 Stack의 Adt이다!
  
![stacktrace.png](mdimageresource%2Fstacktrace.png)  

**배열과 링크드 리스트를 사용하여 구현된 방식**

# Stack 사용 예

**자바의 T메모리 구조**  
- 자바 프로그램을 실행하게되면 JVM 구조 내에서  
  - 프로그램이 절차적으로 실행이 되기 시작할 때에 Stack 프레임으로 돌아가는 동작방식을 가지고 있다  

- 아래 코드를 실행할경우 자바의 스택 영역은 다음과같이 진행된다
```java
public class Start2 {
	public static void main(String[] args) {
    	int i;
        i = 10;
        
        double d = 20.0;
    }
}
```
![JavaTmemory.png](mdimageresource%2FJavaTmemory.png)  

2번째 코드까지 실행 시

![JavaTmemory1.png](mdimageresource%2FJavaTmemory1.png)

4번째 코드까지 실행 시  

![JavaTmemory2.png](mdimageresource%2FJavaTmemory2.png)  
6번째 코드까지 실행 시  

위의 흐름대로 자바의 실행과 동시에 실행영역은 Stack에서 사용되는 변수나
사용되는 영역의 흐름들을 스택에 공간하며 다음 실행되는 코드를 만날때마다
새로운 스택의 데이터가 생겨나며
프로그램은 이것을 절차적으로 수행하게 된다!



# Queue
![que.png](mdimageresource%2Fque.png)

큐는 대기열 과 같은 뜻이며  
자료구조에서의 큐는 처리를 기다리는 데이터를 기다리는 줄이라고 보면된다!  
스택과 다르게 먼저 들어간 데이터가 제일 먼저 나오는 방식으로
FIFO(First in First Out) 구조를 가지게 된다  


- enqueue
  - 데이터를 집어넣는 동작
- dequeue
  - 데이터를 빼내는 동작  
  
![deque.png](mdimageresource%2Fdeque.png)  
**dequeue**
  - 삽입과 삭제를 양방향으로 구현이 가능함!  


![CircularQue.png](mdimageresource%2FCircularQue.png)  

**Circular queue**  
- 배열로 구현되어있는 큐를 사용시에
  - 마지막 을 가리키는 인덱스의 공간은 사용하지 못하는 문제의 관점에서 탄생한 자료구조

![Circular1.png](mdimageresource%2FCircular1.png)

![Circular2.png](mdimageresource%2FCircular2.png)   

- 데이터의 삽입이 일어나고 있을 때

![Circular3.png](mdimageresource%2FCircular3.png)  

![Circular4.png](mdimageresource%2FCircular4.png)  

- Dequeue 가 일어나고 있을 때에

# Queue 구현  
- 큐를 구현하는 경우 배열로 이것을 구현할지, 링크드 리스트로 이것을 구현할지 선택이 가능하다  
  - 배열로 구현하는경우 (que)의 영역을 제한하고 싶은 경우
  - 링크드리스트는 동적으로 이 영역을 늘리고 싶은 경우
  
  
## Queue 활용사례
![queexample.png](mdimageresource%2Fqueexample.png)  

- 대기열이라는 관점에서 출발하여
- 비동기 시스템에서 주로 사용된다


메세지 큐 종류에 대해서 먹어보자