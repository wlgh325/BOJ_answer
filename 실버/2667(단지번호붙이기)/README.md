# BOJ 2667번 단지번호붙이기 문제 자바(java)  풀이
- 랭크 : 실버1
- 백준 온라인 저지(BOJ) 2667번 단지번호붙이기 문제 자바 풀이
- [백준 2667번 단지번호붙이기](https://www.acmicpc.net/problem/2667)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
* 1: 집이 있는 곳
* 0: 집이 없는 곳
* 단지: 연결된 집들의 모임
* 연결: 상하좌우로 다른 집이 있는 경우(대각선 X)

지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬해 출력해라!

# 문제풀이
이 문제는 간단하게 dfs를 적용해서 풀 수 있는 문제입니다.  
비슷한 문제로는 [2468번 안전영역](https://www.acmicpc.net/problem/2468)이 있습니다.  
1. 모든 위치에서의 dfs 함수를 실행합니다. (값이 1인 곳만)
2. 실행하고 dfs로 탐색하면서 방문 처리를 해줍니다.
3. dfs로 방문할때마다 count를 증가시키며 단지내 집이 몇개 인지 체크합니다.
4. 단지내 집의 수를 list에 넣고 sorting하여 출력합니다.


# dfs
이 문제의 풀이가 2468번의 안전영역과 거의 똑같기 때문에 안전영역의 설명을 참조해주세요!!
[2468번 안전영역 풀이](https://hoho325.tistory.com/98)
dfs 함수는 다음과 같이 구현하였습니다.  
1. xdir과 ydir을 통해 탐색할 방향을 제어합니다.
    * 예를 들어 0번째 인덱스를 참조하면 x에는 -1을 더하고 y에는 0을 더합니다.  
    * 즉 행은 1 감소, 열은 그대로기 때문에 위로 이동한다는 것을 알 수 있습니다.
2. dfs이므로 stack을 통해 깊이 우선 탐색을 합니다. 처음 위치를 stack에 넣습니다.
3. stack에 값이 있는 동안 계속 반복합니다.
    * stack에서 값을 하나 꺼내고 방문하지 않았다면 방문처리합니다.
    * 방문 했다는 것은 단지내 하나의 지역을 뜻하기 때문애 지역의 개수를 증가시킵니다.
    * 그리고 그 위치에서 상하좌우 탐색하며 방문하지 않은 곳이 있다면 stack에 넣고 다음에 방문합니다.(이때 배열의 범위를 넘지 않는지 check)
4. 마지막으로 단지내 지역수를 return하고 종료합니다.

```java
	public static int dfs(int x, int y) {
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = {-1,1,0,0};
		int[] ydir = {0,0,-1,1};
		int count = 0;
		Stack<Node> stack = new Stack<>();
		
		stack.add(new Node(x,y));
		while(!stack.isEmpty()) {
			Node n = stack.pop();
			
			// 방문했는지 확인
			if(map[n.a][n.b] == 1) {
				int ax, ay;
				
				// 방문 처리
				map[n.a][n.b] = 0;
				count++;
				// 4방향 보기
				for(int i=0; i<4; i++) {
					ax = n.a + xdir[i];
					ay = n.b + ydir[i];
					// 범위를 넘지 않는지 확인
					if(isCorrect(ax, ay)) {
						if(map[ax][ay] == 1)
							stack.add(new Node(ax, ay));
					}
				}
			}
		}
		
		return count;
	}
```