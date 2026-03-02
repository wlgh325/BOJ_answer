# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 14179번 빗물 문제 자바(java)  풀이</span>
- 랭크 : 골드4
- [백준 14179번 빗물](https://www.acmicpc.net/problem/14179)
  <br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 비가 오면 블록 사이에 빗물이 고인다
2. 고이는 빗물의 총량을 구하라.
3. 빗물이 전혀 고이지 않을 경우 0 출력
   <br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
1. H와 W가 크지 않기 때문에 처음부터 모든걸 검사하는 방법이 있다.
- 한 줄씩 왼 쪽 아래부터 확인한다
- 빈 공간이 없었고 벽이 나온 경우 왼쪽 벽 처리
- 왼쪽 벽이 있었고 빈 공간이 나온 경 빈 공간 처리
- 왼쪽 벽이 있었고 또 벽이 나온 경우 오른쪽 벽으로 생각한다. 이 빈 공간을 더해나간다.

2. 빗물이 모이는 상황을 파악하고 공식화 한다.
- 왼쪽에서 가장 높은 벽: leftMax
- 오른쪽에서 가자 높은 벽: rightMax
- 고이는 물: min(왼쪽 치ㅗ대, 오른쪼 최대) - 현재 높이

