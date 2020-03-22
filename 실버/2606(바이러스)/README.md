# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 2606번 바이러스 문제 자바(java)  풀이</span>
- 랭크 : 실버2
- [백준 5430번 AC](https://www.acmicpc.net/problem/5430)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결된 모든 컴퓨터는 웜 바이러스에 걸린다.
2. 1번 컴퓨터가 바이러스에 감염 되었을때, 1번 컴퓨터를 통해 걸리게 되는 컴퓨터 수를 출력하시오
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
1번 컴퓨터와 연결된 모든 점을 탐색하면 됩니다. 즉 bfs를 이용하여 탐색합니다.  
그 외에 플로이드-와샬 알고리즘을 이용해서 풀거나 disjoint set을 이용해서 풀 수도 있습니다.
- <span style="color: blue; font-size:13pt">BFS(Main.java)</span>
1. 입력으로 주어진 연결 정보를 바탕으로 인접 행렬을 만듭니다.
2. 큐를 이용하여 bfs 함수를 작성하여 1과 연결된 점들을 탐색해 나가며 탐색한 노드의 개수를 구합니다.
3. 1은 개수에서 제외시켜야 되기 때문에 cnt를 -1 부터 시작합니다.
<br>

- <span style="color: blue; font-size:13pt">Disjoint Set(Main2.java)</span>
Union find 관련 내용을 따로 찾아보시거나 다음의 [크루스칼 알고리즘](https://hoho325.tistory.com/113)을 참고하세요!!
1. 각 노드의 부모를 저장할 parent 배열과 각 노드의 크기를 저장할 size 배열을 선언하고 초기화 합니다.
    parent[x] = y : x의 부모는 y 이다.
    size[x] = y : x 노드의 크기는 y 이다.
2. 값을 입력 받아서 부모가 같지 않은 경우 부모를 합쳐줍니다.
3. 부모를 합칠 때 y의 부모를 x로 바꿔주고 x밑에 y를 부모로 하던 모든 노드를 가지는 것이므로 x의 크기에 y의 크기를 더해줍니다.
4. disjoint set을 모두 완성한 후, 1번 노드(0번째)의 부모를 찾아서 그 노드의 size에서 1을 뺀 값(1번 컴퓨터 제외)을 출력합니다.