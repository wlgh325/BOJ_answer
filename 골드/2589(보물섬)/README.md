# BOJ 2589번 보물섬 문제 자바(java)  풀이
- 랭크 : 골드5
- 백준 온라인 저지(BOJ) 2589번 보물섬 문제 자바 풀이
- [백준 2589번 보물섬](https://www.acmicpc.net/problem/2589)

## [티스토리 주소](https://hoho325.tistory.com/85?category=780777)

# 문제정리
1. 각 칸은 육지(L)나 바다(W)로 나뉘어져 있다.
2. 이동은 상하좌우로 이웃한 육지로만 가능하다. 한 칸 이동하는데 **한시간** 걸린다.
3. 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어져 있다.
* 육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나거나, 멀리 돌아가면 안된다.

## 문제풀이
어느 한 지점에서 얼마나 떨어져 있는지를 서로 따지면 된다.  
즉 dfs가 아닌 bfs로 따져가면 된다.  
이는 인적성 문제에서 등장하는 두 점사이의 거리를 이동하는 최단 경로를 구하는 문제와 같다.
예를들어 아래와 같이 지도가 주어졌다고 하자. (W는 생략)
```
 LL
LLL
L L
L L
 LL
```

이제 왼쪽 맨위부터 차례대로 서로 떨어진 거리를 탐색한다고 가정한다. 그랬을 경우 다음과 같이 거리가 정해진다.
```
 01
212
3 3
4 4
 65
```
이 경우 가장 먼 곳 까지의 거리는 6이 된다. 이렇게 최대 값을 찾아나가면 된다.

## bfs level 나누기
bfs는 큐를 이용하여 구현한다.
1. 우선 시작점을 방문처리하고 큐에 넣는다.
2. queue에 아무것도 남은게 없을때까지(방문하지 않은 곳이 없을때까지) while문을 돌린다.
3. 그리고 level 별로 나누기 위해서 큐에 있는 점을 먼저 방문한다.(queue의 size만큼 다 돈다.)
4. 점을 방문하면서 4가지 방향모두 검사하며 방문하지 않은 연결된 점이 있는지 찾고 다음 레벨에 방문하기 위해서 queue에 넣는다.

위의 예시 처럼 땅이 있다고 하자. 그러면 다음과 같은 순으로 queue를 비우며 level을 관리한다.
- level 0: (0,0)
- level 1: (1,1), (0,2)  -> 1,1과 0,2는 0,0에서 연결된 땅
- level 2: (1,0), (1,2) -> 1,0은 위의 1,1에서 연결된 땅, (1,2)는 (0,2)에서 연결된 땅
- level 3: (2,0), (2,2) -> (2,0)은 위의 (1,0)에서 연결된 땅, (2,2)는 (1,2)에서 연결된 땅
- level 4: (3,0), (3,2) -> (3,0)은 위의 (2,0)에서 연결된 땅, (3,2)는 (2,2)에서 연결된 땅
- level 5: (4,2) -> (4,2)는 (3,2)에서 연결된 땅
- level 6: (4,1) -> (4,1)은 (4,2)에서 연결된 땅
=> 이렇게 (0,1)에서 시작해서 가장 먼 땅까지의 거리는 6이된다.
