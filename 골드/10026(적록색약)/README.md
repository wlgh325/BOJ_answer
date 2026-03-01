# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 10026번 적록색약 자바(java) 풀이</span>
- 난이도: 골드5
- [백준 10026번 폰트](https://www.acmicpc.net/problem/10026)
  <br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 크기 NxN, R/G/B 중 하나 색칠 되어 있음
2. 구역이 나뉘어져 있으며, 같은 구역은 같은 색
3. 같은 색상이 상하좌우로 이어져 있는 경우 같은 구역
4. 적록색약은 빨강과 초록을 같게 본다
5. 적록색약이 봤을때와 아닐때의 구역의 수 구하기
   <br><br>

# <span style="color: red; font-size:15pt">문제 접근</span>
1. 구역 찾기
구역을 나누는 문젤 dfs를 수행합니다.  
적록색약의 조건이 없는 경우에는 시작한 문자와 같은지 계속 확인하도록 하면 됩니다.  
하지만 적록색약 조건으로 인해 'R', 'G'의 경우에는 같은 경우로 처리해야합니다.  
그래서 문자에 따라 다른 Set을 전달하여 Set에 포함되는 경우 같은 문자로 판단하도록 하였습니다.
   <br><br>

# <span style="color: red; font-size:15pt">문제 풀이(Main.java)</span>
1. 입력을 받습니디.
2. 'R'과 'G'를 같은 문자로 처리할 set과 각각 문자를 처리하기 위한 set을 만듭니다
3. 각 문자에 따라 맞는 set을 넘기도록 하며 일반적인 bfs과정을 거칩니다.
4. 적록색약이 아닌 경우와 적록색약인 경우 따로 bfs를 돌립니다.
   <br><Br>
