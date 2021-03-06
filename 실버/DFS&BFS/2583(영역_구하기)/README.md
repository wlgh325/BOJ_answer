# BOJ 2583번 영역 구하기 문제 자바(java)  풀이
- 난이도: 실버1
- 풀이시간: 30분
- [백준 2583번 영역 구하기](https://www.acmicpc.net/problem/2583)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. MxN 크기의 모눈종이가 주어진다.
2. 모눈 종이 위에 K개의 직사각형을 그린다.
3. k개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.
4. 몇개의 영역으로 분리되는지, 각 영역의 넓이는 어떤지 구하여라

# 문제풀이
1. 분리된 영역과 넓이를 구하는 것이므로 모든 좌표에서 bfs 탐색을 하면 됩니다.
    이 문제는 영역을 구분짓는 문제인 [보물섬](https://www.acmicpc.net/problem/2589), [치즈](https://www.acmicpc.net/problem/2636), [빙산](https://www.acmicpc.net/problem/2573)과 유사합니다.
2. 문제에서 주어지는 직사각형의 좌표들과 배열의 인덱스가 다르기 때문에 주어진 x,y좌표를 반대로 바꿔줍니다.
3. 직사각형이 있는 곳은 map에 1로 표시합니다.
4. 그리고 유효한 인덱스이며 map에 1로 표시되어있지 않은곳만 방문하며 영역의 넓이를 구합니다.
5. Collections.sort를 이용하여 오름차순 정렬합니다.
6. 영역의 개수는 bfs 함수를 부른 횟수로 계산할 수 있습니다.
dfs나 bfs 상관 없이 통과할 수 있습니다.