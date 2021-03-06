# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 1012번 유기농 배추 문제 자바(java)  풀이</span>
- 난이도: 실버2
- 풀이시간: 20분
- [백준 1012번 유기농 배추](https://www.acmicpc.net/problem/1012)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다.
2. 배추가 상하좌우로 붙어있으면 인접해있다고 본다.
3. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 서로 퍼지므로 총 몇 마리의 지렁이가 필요한지 알 수 있다.
4. 다음과 같이 입력이 주어진다
```
TC 개수
가로 세로 배추개수
맵
```
5. 각 TC에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.
<br><br>

# <span style="color: red; font-size:15pt">문제 접근</span>
이 문제는 bfs를 이용하여 영역의 개수를 구하는 것과 같은 문제입니다.  
bfs를 통해 1로 이어져 있는 영역은(배추가 이어져 있는 영역) 방문 체크됩니다.  
그렇게 방문되어 있지 않은 지역이면서 1인 영역을 방문해나가면 영역의 개수를 구할 수 있습니다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
1. tc 개수를 입력받습니다.
2. 가로, 세로, 배추의 개수를 입력받습니다.
3. map 2차원 배열을 생성하고 입력받은 배추의 위치에 1의 값을 넣어 배추가 있음을 표시합니다
4. 방문체크 배열을 생성하고 2중 for문으로 맵에서 1이고 방문하지 않았다면 bfs를 통해 탐색합니다.
    탐색할 수 있다는 것은 하나의 다른 영역이 있다는 것이므로 영역의 개수를 1증가 시켜줍니다.
5. 영역의 개수(ans)를 출력합니다.
<br><br>

# <span style="color: red; font-size:15pt">bfs</span>
1. 시작지점을 방문처리 합니다.
2. 시작지점을 queue에 넣습니다.
3. while문으로 queue에 있는 숫자를 하나씩 꺼내며 bfs 탐색합니다.
4. 그리고 상,하,좌,우, 차례대로 인접한 배추가 있는지 탐색합니다.
    탐색할 때, 유효한 위치인지? 방문한적 없는지? 배추가 있는 곳인지? 검사한다.
    조건을 만족한다면 queue에 새로운 위치를 넣고 바로 방문처리 한다.
5. queue가 빌 때까지 반복한다