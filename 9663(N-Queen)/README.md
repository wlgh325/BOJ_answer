# BOJ 9663번 N-Queen 문제 자바(java)  풀이
- 랭크 : 실버 1
- 백준 온라인 저지(BOJ) 9663번 N-Queen 문제 자바 풀이
- [백준 9663번 N-Queen](https://www.acmicpc.net/problem/9663)

## [티스토리 주소](https://hoho325.tistory.com/)

## 문제이해
```
이 문제는 백트래킹을 이용해서 풀 수 있는 문제로 완전탐색을 통해 모두 찾아봐야 한다.
체스를 둘 줄 모르는 분들을 위해서 간략히 룰에 대해서 설명하겠다.
체스 말 중 퀸(Queen)은  상하좌우, 주대각선, 부대각선 방향 모두 이동할 수 있다.
전 방향으로 이동해서 모든 말 들을 공격할 수 있는 체스의 핵심 말이라 할 수 있다. (몇 칸이든 이동 가능)

```

```
1 x x x
x x x x
x x x x
x x x 1
예를 들어 4x4 체스 판 배열에 말이 두개가 위 처럼 놓여있다면
대각선 방향에 존재하므로 서로 공격할 수 있다.

즉 공격 할 수 없게 하기 위해서는 상하좌우, 대각선 어느 위치에든 말이 없어야 한다.

```
## 문제 풀이
1. 체스의 정보를 관리하는 체스 판 2차원 배열을 만든다.
2. 그리고 백트래킹 함수를 통해 체스 판의 모든 위치를 순회힌다.
    * 2.1 퀸을 놓은 곳은 1로 놓여있지 않은 곳은 0으로 표시한다.
    * 2.2 말을 놓을 곳의 상하좌우, 대각선 모든 위치를 검사하여 없는 경우만 말을 놓는다.
3. 말을 놓을 수 있는지 검사하는 부분은 switch문을 이용하였다.
4. 그렇게 체스 판의 맨 밑에줄 까지 모든 말을 채운 경우(num == n) 경우의 수 하나를 증가시켜 준다.