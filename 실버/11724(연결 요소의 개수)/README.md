# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 11724번 연결 요소의 개수 c++ 및 java 풀이</span>
- 난이도: 실버3
- [백준 11724번 연결 요소의 개수](https://www.acmicpc.net/problem/11724)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
방향 없는 그래프가 주어질때 그래프의 연결 요소(Connected Component)의 개수를 구하시오
<br><br>

# <span style="color: red; font-size:15pt">연결 요소 란??</span>
그래프는 여러 개의 고립된 그래프로 구성될 수 있는데  
서로 연결된 여러 개의 고립된 그래프 각각을 연결 요소라고 한다.
1. 연결 요소의 특징
- 연결 성분에 속한 모든 정점을 연결하는 경로가 있어야 한다.
- 또 다른 연결 성분에 속한 정점과 연결하는 경로가 있으면 안된다.
BFS, DFS를 통해 시작 정점으로부터 도달 가능한 모든 정점들이 하나의 연결성분이 된다.
<br><br>

# <span style="color: red; font-size:15pt">문제 접근</span>
처음에 주어진 예제를 잘못 보고 문제를 이해를 잘 못해서 빨리 풀지 못했습니다 ㅠㅠㅠ  
예제를 똑바로 봅시다!!!
1. 하나의 정점에서 시작해서 bfs나 dfs를 통해 연결된 점들을 탐색하며 방문 여부를 저장해주면 됩니다.
2. 그렇게 하면 하나의 정점에서 연결된 점들을 모두 탐색하게 되고 하나의 연결 요소를 찾게된 것입니다.
3. 다음 탐색시에 방문여부를 가지고 판단하여 방문하지 않은 경우 다시 bfs나 dfs를 통해 탐색하게 되면 이는 또 다른 새로운 연결요소를 탐색함을 의미합니다.
<br><br>

# <span style="color: red; font-size:15pt"java 문제 풀이</span>
1. 좀 더 효율적인 탐색을 위해 2차원 arraylist로 graph 정보를 저장합니다.
2. 저장하기 위해 초기화를 합니다
3. 그래프 연결 정보를 받아 graph 변수에 저장합니다.
    무방향 그래프이기 때문에 반대로도 연결정보를 저장해주어야 합니다.
    예를들어 1과2가 연결되어있다면 2와 1도 연겨되어 있는 것이므로 반대로도 저장해주어야 합니다.
4. 방문하지 않았다면 bfs를 통해 방문합니다.
5. bfs는 시작 정점에서 연결된 정점들을 차례차례 방문해나갑니다.
6. 방문하지 않았다면 방문처리 하고 queue에 다음 탐색을 위해 넣습니다.
7. 이를 queue가 빌 때까지 반복합니다.
8. bfs 함수를 진입하는 횟수가 연결요소의 개수가 됩니다.
<br><br>

# <span style="color: red; font-size:15pt">c++ 문제풀이</span>
c++도 java와 로직은 똑같습니다. 다만 2차원 arrayList 대신 2차원 vector를 사용합니다.
1. 2차원 벡터를 초기화합니다. vector에 N개의 빈 vector를 넣어주면 N개의 vector를 담은 2차원 vector가 됩니다.
2. graph.at(a).push_back(b) 은 자바의 graph.get(a).add(b)와 같습니다.
    **vector에서 get은 at**
    **vector에서 add는 push_back**
3. bfs도 마찬가지입니다. 다만 다른점은 vector의 iterator를 생성하여 그 정점과 연결된 다른 정점들을 하나씩 보며 방문여부를 확인합니다. 자바에서는 다음 구문과 같습니다.
    ```
    for(int b : graph.get(a)){
        // 생략
    }
    ```
4. iterator의 시작을 begin으로 받아서 포인터를 하나씩 증가시키면서 값을 참조할 수 있습니다. iterator의 끝은 end로 확인합니다.
<br><br>