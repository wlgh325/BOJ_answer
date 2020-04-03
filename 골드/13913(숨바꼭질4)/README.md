# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 13913번 숨바꼭질4 자바(java)  풀이</span>
- 랭크 : 골드4
- [백준 13913번 숨바꼭질4](https://www.acmicpc.net/problem/13913)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 수빈이는 현재 점 N에 있다. 동생은 점 K에 있다.
2. 수빈이는 1초 후에 N-1, N+1, Nx2의 위치로 이동할 수 있다.
3. 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇초 후 인가??
4. 그때의 이동 경로도 찾아야 한다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이1</span>
bfs를 이용하면 풀 수 있는 문제입니다.  
위치를 저장하기 위해서는 ArrayList를 이용합니다.  
이 방법이 바로 떠오른 방법입니다. 하지만 메모리는 물론 시간이 많이 걸립니다.(list를 copy 하는 시간 때문에)
1. Pair class를 만들어서 Pair형 Queue를 만들고 시작 값을 queue에 넣어주고 방문처리도 해줍니다.
2. Queue에서 값을 하나 꺼냅니다.
3. 꺼낸 값이 동생의 위치와 같은 경우 min 값과 시간을 비교합니다
    min보다 꺼낸 time 값이 작다면 min 값을 update 해주고
    minList도 update 해줍니다.
4. 이동할 수 있는 세가지 경우 모두에 대해서 방문하지 않았고 이동이 가능한 경우 queue에 넣고 방문할 수 있도록합니다.
    이때 list에 이동한 위치의 값을 추가한 list를 새로 만들어 Pair에 저장하여 queue에 넣습니다.
5. queue가 빌때까지 반복합니다.
<br>

# <span style="color: red; font-size:15pt">문제 풀이2</span>
더 빠르게 푸는 방법으로는 배열을 이용하는 방법입니다.  
그 이전 위치를 배열에 저장하는 방법입니다.  
예를들어 a에서 b로 이동했다면 parent[b] = a 로 저장합니다.
나머지 로직은 모두 같으므로 이동한 위치만 설명하겠습니다.
1. 도착한 위치(동생 위치)를 list에 넣습니다.
2. 그 위치 전의 위치를 찾습니다(parent[aa])
3. 이제 시간 길이 만큼 parent 배열을 이용해 이 전의 위치를 계속 찾아 나갑니다.
    예제를 보면 parent[17] = 16 입니다.
    그러면 16을 넣고 다시 parent[16]을 찾으면 8 입니다
    다시 8을 넣습니다. 그리고 parent[8]을 찾습니다. 4 입니다
    4를 넣고 parent[4]를 찾습니다. 5입니다.
    마지막으로 5를 넣고 종료합니다. (4초 전의 위치까지 찾았으므로)
