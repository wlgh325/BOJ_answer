# BOJ 14502 연구소 문제 자바(java)  풀이
- 랭크 : 골드5
- 백준 온라인 저지(BOJ) 14502 연구소 문제 자바 풀이
- [백준 14502 연구소](https://www.acmicpc.net/problem/14502)

## [티스토리 주소](https://hoho325.tistory.com/)

# 목차
[1. 문제정리](#문제정리)  
[2. dfs](#dfs)  
[3. 문제풀이](#문제풀이)

# 문제정리
* 0: 빈칸
* 1: 벽
* 2: 바이러스
1. 바이러스는 상하좌우로 인접한 빈 칸 모두로 퍼져나갈 수 있다.
2. 새로 세울 수 있는 벽의 개수는 3개이며 꼭 3개 모두 세워야 한다.
3. **안전영역**: 벽 3개를 세운 뒤, 바이러스가 퍼질 수 없는 곳

* 안전영역 크기의 최댓값을 구하여라!

# dfs
두 가지 방법 중에 원하는 알고리즘을 사용하면 된다.  
코드 길이만 본다면 재귀를 이용하는게 더 간결하다.

## stack이용
```java
	public static void dfs(int x, int y){
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		Stack<Pos> stack = new Stack<>();
		boolean[][] visited = new boolean[height][width];

		stack.push(new Pos(x,y));
		while(!stack.isEmpty()){
			Pos pos = stack.pop();
			int a = pos.a;
			int b = pos.b;
			visited[a][b] = true;
			
			for(int i=0; i<4; i++){
				int dx = a + xdir[i];
				int dy = b + ydir[i];
				if(isValidPosition(dx, dy)){
					if(!visited[dx][dy] && map2[dx][dy] == 0){
						stack.push(new Pos(dx, dy));
						visited[dx][dy] = true;
						map2[dx][dy] = 2;
					}
				}
			}
		}
	}
```

## 재귀 이용
```java
	public static void dfs(int x, int y){
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};

		for(int i=0; i<4; i++) {
			int dx = x + xdir[i];
			int dy = y + ydir[i];
			if(isValidPosition(dx, dy)) {
				if(map2[dx][dy] == 0) {
					map2[dx][dy] = 2;
					dfs(dx,dy);
				}
			}
		}
	}
```

# 문제풀이
## 조합(combination) 이용
**Main** 코드 참고
1. 비어있는 곳을 모두 찾아서 그 중 3곳을 골라 모든 경우를 다 따지며 벽을 세운다.
2. 벽을 세울 때마다 바이러스가 있는 곳에서 bfs/dfs를 통해 퍼져나간다.
3. 퍼져 나간뒤 0의 개수를 세서 안전 영역의 크기를 구해 list에 저장하고 max 값을 구한다.

## 백트래킹 이용 
**Main2** 코드 참고
이 방법은 조합을 따로 구하지 않고 즉, visited를 이용할 필요 없이 더 간단하게 구할 수 있다.  
즉 조합을 구하는 방식으로 백트래킹을 해가면 1을 써줬다 지웠다 해주면 된다.

### 2차원 배열의 백트래킹
```
2차원 배열의 크기가 arr[width][height]라고 가정하자
1차원 배열의 백트래킹은 간단하지만 2차원 배열의 백트래킹을 위해 x,y 좌표를 다음과 같은 방식으로 구한다.
int x = i/width;
int y = i%width;
조금만 생각해보면 위의 식이 i를 증가시키며 2차원 배열을 순회할 수 있다는 것을 알 수 있다.

width로 나누어서 몫을 구해 몇 번째 row인지 제어하고
나머지를 통해 column의 index를 제어할 수 있기 때문이다.
* width = 3, height=5

|i|x|y|
|:---:|:---:|:---:|
|0|0|0|
|1|0|1|
|2|0|2|
|3|1|0|
|4|1|1|
|5|1|2|
|6|2|0|
```
