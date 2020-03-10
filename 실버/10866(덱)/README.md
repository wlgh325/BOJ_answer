# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 10866번 덱 문제 자바(java)  풀이</span>
- 랭크 : 실버4
- 풀이시간: 15분
- 메모리: 19256 KB
- 시간: 264 ms
- [백준 10866번 덱](https://www.acmicpc.net/problem/10866)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 덱(Deque)를 구현한 다음 명령들을 수행하여라.
2. push_front X: 정수 X를 덱의 앞에 넣는다.
3. push_back: X: 정수 X를 덱의 뒤에 넣는다.
4. pop_front: 덱의 가장 앞에 있는 수를 빼고, 출력한다. 만약 덱이 비었다면 -1 출력
5. pop_back: 덱의 가장 뒤에 있는 수를 빼고, 출력한다.  만약 덱이 비었다면 -1 출력
6. size: 덱에 들어있는 정수 개수 출력
7. empty: 덱이 비어있다면 1 아니면 0
8. front: 덱의 가장 앞에 있는 수 출력. 덱이 비었다면 -1 출력
9. back: 덱의 가장 뒤에 있는 수 출력. 덱이 비었다면 -1 출력
push_front와 push_back을 제외하고 모두 출력해야 한다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
Deque는 Double-Ended Queue의 줄임말 입니다.  
말 그대로 큐의 양쪽 끝에서 삽입과 삭제가 모두 일어날 수 있는 queue입니다.  
어떻게 사용하느냐에 따라 스택이 될 수도 큐가 될 수도 있습니다.  
자바에 구현된 Deque를 선언하여 명령에 맞는 메소드를 실행하면 됩니다.
1. push_front: addFirst()를 이용하여 값을 넣습니다.
2. push_back: addLast() 또는 add를 이용하여 값을 넣습니다.
3. pop_front: pollFirst()를 이용하여 제일 앞의 값을 뺍니다.
4. pop_back: pollLast()를 이용하여 제일 뒤의 값을 뺍니다.
5. size: size()를 이용하여 deque의 크기를 구합니다ㅏ.
6. empty: isEmpty()를 이용하여 비어있는지 확인합니다.
7. front: getFirst()를 이용하여 값을 빼지 않고 맨 앞의 값을 가져옵니다.
8. back: getLast()를 이용하여 값을 빼지 않고 맨 뒤의 값을 가져옵니다.

# <span style="color: red; font-size:15pt">자바 Deque 선언</span>
Deque는 interface이므로 구현된 class들을 이용하여 선언합니다.  
여러가지 deque들이 있습니다.
1. ArrayDeque
2. ConcurrrentLinkedDeque
3. LinkedBlockingDeque
4. LinkedList
그 중에서 ArrayDeque를 이용하여 선언합니다.  
```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> deque = new ArrayDeque<>();
```