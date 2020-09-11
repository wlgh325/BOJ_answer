# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 이분 그래프 자바(java)  풀이</span>
- 난이도: 골드4
- [백준 1707번 이분 그래프](https://www.acmicpc.net/problem/1707)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 이분 그래프(Bipartite Graph): 그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있는 그래프
2. 그래프가 입력으로 주어질 때, 이 그래프가 이분 그래프가 맞는지 판별하여라.
3. 여러 테스트 케이스가 주어지며 TC의 첫 줄에는 V의 개수와 E의 개수가 주어진다.
4. 그리고 이어서 간선에 대한 정보(인저한 두 정점의 번호)가 주어진다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
0. 그래프를 arraylist에 담는다.
1. bfs를 작성한다.
2. 처음에 임의의 색깔로 색칠한다(파란색)
3. 인접한 정점들을 방문하며 색을 칠한다. 방문하지 않았다면 다른 색을 칠한다.
4. 만약 이미 방문한 정점인데 같은 색깔이라면 이는 이분 그래프가 아니므로 while문을 탈출한다.
5. 이와 같은 과정을 모든 정점에 대하여 반복한다.
<br><br>