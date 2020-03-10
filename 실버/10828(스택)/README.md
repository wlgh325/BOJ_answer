# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 10828번 스택 자바(java)  풀이</span>
- 랭크 : 실버4
- 메모리: 17728KB
- 시간: 252ms
- [백준 10828번 스택](https://www.acmicpc.net/problem/10828)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 스택을 구현하고 다음 명령들을 처리해라.
2. push X: 정수 X를 스택에 넣는다.
3. pop: 스택에서 가장 위에 있는 정수를 빼고, 출력 한다. 스택이 비었다면 -1 출력
4. size: 스택의 크기를 출력한다.
5. empty: 스택이 비었다면 1 아니면 0을 출력한다.
6. top: 스택의 가장 위에 있는 정수를 출력한다. 만약 비어있다면 -1 출력
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
이 문제는 [큐](https://www.acmicpc.net/problem/10845)문제와 유사합니다. 이도 풀어보시는걸 추천드립니다!!  
java에 구현되어 있는 Stack을 이용하여 문제를 풉니다. Stack의 메소드 몇 개만 알면 풀 수 있습니다.  
stack을 사용하기 위해서는 다음을 추가해야 합니다.
```java
import java.util.Stack;
```
1. push: push() 메소드를 이용하여 값을 넣습니다.
2. pop: pop() 메소드를 이용하여 값을 빼고 출력합니다.
3. size: size() 메소드를 이용하여 스택의 크기를 출력합니다.
4. empty: isEmpty() 메소드를 이용하여 비어있는지 확인하고 값을 출력합니다. 비어있다면 true를 반환합니다.
5. top: peek() 메소드를 이용하여 스택의 가장 위에 있는 정수를 출력합니다. 이는 이 메소드를 사용하지 않고 가장 마지막에 넣은 값을 이용하여 출력할 수도 있습니다.
