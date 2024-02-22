# JVM 그리고 자바의 동작구조

> 작성자 : [장성근](https://github.com/heoap9)

<details>
<summary>Table of Contents</summary>

- [JVM이란?](#JVM)
</details>

---

## JVM
"**Java Virtual Machine**"은 java로 개발한 프로그램을 컴파일하여 만들어진 바이트코드를 실행시키기 위한"
가상머신이다
일반적인 프로그래밍 언어의 컴파일 과정과는 다르게 자바는 Java코드만을 사용하기 위한 가상머신이 존재하며
이 가상머신에 동작하기 위한 JRE(Java Runtime Environment)이 포함되어 있으며
자바의 프로그램 동작 과정을 설명하며 JVM의 구조를 설명하겠습니다



## Java의 컴파일과정
![CompileC.png](ImageSource%2FCompileC.png)
> 각각의 환경에 맞는 컴파일러를 사용해야 한다  
  
C언어를 사용하는경우 각각 환경에 제공되는 컴파일러를 교체하며 사용하고  
각각 환경에 맞는 라이브러리 및 사용환경을 다르게 구축되어있으며  
다른 환경에서 작성한 코드가 범용적으로 모든 환경에서 작동하지 않아 환경구성에 있어 문제를 겪을 수 있다  

위 그림에서 볼 수 있듯이 각각 환경에 대응되는 컴파일러를 다르게 써야만 그 환경에 맞는 프로그램을 구동할 수 있다  
![JavaCompile.png](ImageSource%2FJavaCompile.png)  
> JVM이 있기에 코드와 컴파일러가 환경에 구애받지 않는 환경을 가진다  

자바는 독특합니다  
이러한 환경에 따른 개별적인 구동점이 다르기 때문에 운영체제가 다르더라도, 환경이 다르더라도
jvm에 전달되는 목적 코드의 스펙을 따르기 때문에 어떠한 환경에서 작성된 코드더라도 똑같이 구동됩니다  

자바의 컴파일 과정은 다음과 같습니다 
![Javacompile1.png](ImageSource%2FJavacompile1.png)  

- 원본 코드를 먼저 컴파일 하게되면 **JavaC** 컴파일을 진행하며 JavaByte코드를 먼저 생성합니다  
- 이 Java Byte코드를 JRE에 전달하게되면 JVM이 구동되기 시작합니다
- JVM이 구동되고 나면 내부 JIT컴파일러가 이를 인터프리터로 해석하며 컴파일하여 코드를 실행하게됩니다

![JVM.png](ImageSource%2FJVM.png)  

자바는 OS에게서 할당받은 자원을 활용해(메모리) 모든 구동영역을 시스템 메모리 계층의 heap 영역에 할당하여 구동합니다  
(가상환경의 특성)  
이 할당받은 영역에서 jvm은 메모리의 영역을 나누어 구동하는 영역을 따로 구축하여 사용하게 됩니다

