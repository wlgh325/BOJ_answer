# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 12851번 숨바꼭질2 자바(java)  풀이</span>
- 랭크 : 골드5
- [백준 12851번 숨바꼭질2](https://www.acmicpc.net/problem/12851)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 수빈이는 현재 점 N에 있다. 동생은 점 K에 있다.
2. 수빈이는 1초 후에 N-1, N+1, Nx2의 위치로 이동할 수 있다.
3. 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇초 후 인가??
4. 가장 빠른 방법의 수도 찾아서 출력해라.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
bfs를 이용하면 풀 수 있는 문제입니다.
1. Pair class를 만들어서 Pair형 Queue를 만들고 시작 값을 queue에 넣어주고 방문처리도 해줍니다.
2. Queue에서 값을 하나 꺼냅니다. 그리고 방문 처리 합니다.
    queue에 넣을때 방문처리 하지 않고 꺼낼때 방문처리 합니다.
    queue에 넣을때 방문처리 하게 되면 모든 가능한 경우를 따질 수 없어 개수를 제대로 셀 수 없습니다.
    ex) 1 4
3. 꺼낸 값이 동생의 위치와 같은 경우 min 값과 시간을 비교합니다
    min보다 꺼낸 time 값이 작다면 min 값을 update 해주고
    min값과 tt 값이 같다면 같은 시간으로 갈 수 있는 다른 경우 이므로 cnt 값을 증가시킵니다.
4. 이동할 수 있는 세가지 경우 모두에 대해서 방문하지 않았고 이동이 가능한 경우 queue에 넣고 방문할 수 있도록합니다.
5. queue가 빌때까지 반복합니다.
<br>