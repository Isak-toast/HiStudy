# 프로세스와 스레드 차이점

작성자 : [배소라](https://github.com/sorayayat)
### OS - Operating System

## 1. os

1) os란?
    프로그램이 실행에 필요한 요소들을 시스템 자원이라 합니다. (CPU, 메모리, 보조기억장치, 입출력 장치)
    즉 모든 프로그램은 실행하기에 앞서 자원을 필요로 합니다.

    여기서 실행할 프로그램에 자원을 할당하고, 올바르게 실행하도록 돕는 특별한 프로그램이 바로 **os(운영체제)** 입니다.
    그래서 운영체제 또한 프로그램과 같은 메모리에 적재되어야 합니다.
    다만 특별한 프로그램이기 때문에 항상 커널영역에 적재되어 실행됩니다. 이 영역을 제외한 나머지 영역은 사용자가 이용하는 응용 프로그램이 적재되는 영역, 사용자 영역이라고 합니다.

2) 커널
    os가 제공하는 서비스는 다양하지만 그 중 가장 핵심적은 기능은 자원에 접근하고 조작하는 기능, 프로그램이 올바르게 실행 되게 하는 기능이 핵심 서비스에 속합니다.

    이러한 os의 핵심 서비스를 담당하는 부분을 커널(Kernel)이라고 합니다.


## 2. process & Threads

1) process란?
    실행중인 프로그램을 프로세스라 합니다. 일반적으로는 cpu는 한 번에 하나의 프로세스만 실행할 수 있기 때문에 여러 프로세스를 번갈아가며 실행되며 운영 체제로 부터 시스템 자원을 할당 받아 실행됩니다.


|  자원구조 |
| --- |
| stack |
|  동적영역 |
|  heap |
|  data |
| text |

| 영역 | 설명 |
|---|---|
| text | 작성된 함수나 코드가 cpu가 해석 가능한 기계어 형태로 저장되어 있다. |
| data | 코드가 실행되면서 사용되는 전역 변수나 각종 데이터가 모여있다. 내부는 세분화 되어있다. |
| stack | 지역 변수와 같은 호출한 함수가 종료되면 되돌아올 임시 자료를 저장하는 독립 공간, Stack은 함수의 호출과 함께 할당되며, 함수의 호출이 완료되면 소멸한다. 만일 stack 영역을 초과하면 stack overflow 에러가 발생한다. |
| heap | 생성자, 인스턴스 같은 동적 데이터를 위해 존재하는 공간 |



2) Threads란?
    스레드란, **하나의 프로세스 내에서 동시에 진행되는 작업 갈래, 흐름의 단위**를 말합니다.
    즉, 하나의 프로세스를 생성하면 기본적으로 main 스레드가 생성되고 다음 2,3,4…개 로 프로그래밍하여 위치 시켜주어야 합니다.

    스레드는 프로세스가 할당 받은 자원을 이용하는 실행 단위로써, 스레드끼리 프로세스의 자원을 공유하면서 프로세스 실행 흐름을 일부가 되기 때문에 동시 작업이 가능한 것 입니다.

    > 반면, 프로세스는 다른 프로세스의 메모리에 직접 접근 할 수 없습니다 !

    이때 프로세스의 4가지 메모리 영역 중 스레드는 stack만 할당받고 나머지는 다른 스레드들과 공유하게 됩니다.
    따라서 스레드는 별도의 stack을 가지고 있지만 heap 메모리는 고유하기 때문에 다른 스레드에서 가져와 읽고 쓸 수 있습니다.

    > stack은 함수 호출 시 전달되는 인자, 되돌아갈 주소값, 함수 내에서 선언하는 변수 등을 저장하는 메모리 공간이므로 독립적인 stack을 가졌다는 것은 독립적인 함수 호출이 가능하다 입니다. 이것은 독립적인 실행 흐름을 가졌다라고 할 수 있습니다.

참조 -
https://inpa.tistory.com/entry/%F0%9F%91%A9%E2%80%8D%F0%9F%92%BB-%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4-%E2%9A%94%EF%B8%8F-%EC%93%B0%EB%A0%88%EB%93%9C-%EC%B0%A8%EC%9D%B4
