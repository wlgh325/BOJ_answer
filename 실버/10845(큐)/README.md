# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 10845번 큐 자바(java)  풀이</span>
- 랭크 : 실버4
- [백준 10845번 큐](https://www.acmicpc.net/problem/10845)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 큐를 구현한 다음 주어진 6가지 명령을 처리한다.
2. push X : 정수 X를 넣는다.
3. pop: 큐에서 가장 앞에 있는 정수를 빼고, 출력. 큐가 비었다면 -1 출력
4. size: 큐에 들어있는 원소 개수 출력
5. empty: 큐가 비었다면 '1', 그렇지 않다면 '0' 출력
6. front: 큐의 가장 앞에 있는 정수 출력. 큐가 비었다면 -1 출력
7. back: 큐의 가장 뒤에 있는 정수 출력. 큐가 비었다면 -1 출력
여기서 push 명령을 제외한 모든 명령들은 명령을 수행할때마다 값을 출력해야 합니다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
자바에 이미 구현되어 있는 queue를 이용하여 문제를 해결합니다.  
큐의 메소드들만 알면 간단히 풀 수 있습니다.  
문자열 split을 통해 String배열의 size가 2인 경우는 push 명령. 그 외는 다른 5가지의 명령임을 구분합니다.  
1. front: peek()를 이용하여 맨 앞의 값을 출력합니다.
2. back: 가장 최근에 push로 입력한 값이 맨 뒤에 있으므로 이를 출력합니다.
3. size: size()를 이용하여 큐의 크기를 출력합니다.
4. empty: isEmpty()를 이용하여 출력합니다. 이는 큐가 비었는지 아닌지 알 수 있습니다. 비었다면 true를 반환합니다.
5. pop: poll()을 이용하여 가장 앞에 있는 값을 빼고 출력합니다. poll()의 반환 값은 제거한 수 입니다.
6. push: offer()를 이용하여 값을 큐에 넣습니다.