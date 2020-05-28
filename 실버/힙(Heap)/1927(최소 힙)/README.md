# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 1927번 최소 힙 자바(java)  풀이</span>
- 랭크 : 실버1
- [백준 1927번 최소 힙](https://www.acmicpc.net/problem/1927)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 배열에 자연수 x를 넣는다.
2. 배열에서 가장 작은 값을 출력하고 그 수를 삭제한다.
3. 프로그램은 처음에 비어있는 배열에서 시작한다.
4. 입력으로 주어진 수 x가 자연수이면 그 수를 넣고, 0이면 배열에서 가장 작은 값을 출력하고 제거한다.
5. 입력되는 자연수는 int형이다.
6. 배열이 비어있는데 가장 작은 값을 출력하라고 하는 경우 0을 출력한다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
우선순위 큐를 이용해 값을 오름차순으로 유지합니다. 따로 comparator를 설정하지 않으면 기본 오름차순 입니다.  
Priority 큐는 내부적으로 heap을 이용해 만들어져 있습니다.
1. 입력 받은 값이 0인 경우: 비어있지 않으면 poll해서 출력
    비어있는 경우: 0 출력
2. 입력받은 값이 0이 아닌 경우: 큐에 넣기