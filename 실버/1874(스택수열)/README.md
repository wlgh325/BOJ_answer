# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 1874번 스택 수열 문제 자바(java)  풀이</span>
- 랭크 : 실버3
- 풀이시간: 50분
- 메모리: 29832 KB
- 시간: 316 ms
- [백준 1874번 스택 수열](https://www.acmicpc.net/problem/1874)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 1부터 n까지의 수를 스택에 넣었다가 뽑으면서 주어진 수열을 만들어야 한다.
2. 만들 수 있다면 push와 pop 연산 순서를 출력하고 만들 수 없다면 "NO"를 출력해라.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
처음에 문제 이해가 잘 안되서 헤맸었습니다.  
예제 1번은 다음과 같이 동작합니다.
```
1. 1을 넣는다. (1)
2. 2를 넣는다. (1 2)
3. 3을 넣는다. (1 2 3)
4. 4를 넣는다. (1 2 3 4)
5. 수열의 첫번째 수 4가 stack의 맨 위의 수가 같으므로 pop한다. (1 2 3)
6. 다음 수 3이 stack의 맨 위의 수와 같으므로 pop한다. (1 2)
7. 5를 넣는다. (1 2 5)
8. 6을 넣는다. (1 2 5 6)
9. 세번쨰 수 6이 stakc의 맨 위의 수가 같으므로 pop한다 (1 2 5)
10. 7을 넣는다. (1 2 5 7)
11. 8을 넣는다. (1 2 5 7 8)
12. 수열의 네번째 수 8과 stack의 맨 위의 수가 같으므로 pop한다(1 2 5 7)
13. 이하 똑같이 반복하며 stack이 모두 비게 됩니다.
14. stack이 비게 되었으므로 수열 순서대로 만들 수 있습니다.
```
<br><br>