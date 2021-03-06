# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 1941번 소문난 칠공주 자바(java)  풀이</span>
- 랭크 : 골드3
- [백준 1941번 소문난 칠공주](https://www.acmicpc.net/problem/1941)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 학급은 5x5의 정사각형 형태로 자리가 배치되어있다.
2. 학급이 두 파로 나뉘게 되었다.
3. 이다솜파는 소문난 칠공주 체제를 결성하기로 하였다.
    3.1 7명의 여학생들로 구성된다
    3.2 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
    3.3 반드시 '이다솜파'의 학생들로만 구성될 필요는 없다.
    3.4 그러나 '이다솜파'의 학생은 적어도 4명 이상 반드시 포함되어 있어야 한다.
4. 자리 배치도가 주어졌을 때, 소문난 칠공주를 결성할 수 있는 모든 경우의 수를 구하여라.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
모든 경우를 따져주어야 합니다.
1. 조합을 통해 25개의 자리중 7개를 선택합니다.
2. 우선 선택한 자리중 이다솜파의 자리 개수가 4개 이상인지 판단합니다.
3. 4명 이상 선택되었다면 bfs를 통해 선택한 자리가 모두 연결되어있는지 확인합니다. 연결되어있다면 cnt를 증가시켜줍니다.
여기서 제가 중요하다고 생각하는 테크닉은 선택은 일 차원 배열로 하고 값을 5로 나눈 몫과 나머지를 가지고 행과 열을 빼내야 합니다.  
25개의 크기를 가지는 arr[25]가 있습니다  
여기서 arr = {0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0}과 같이 1,2,6,7,15,16,23 번째 자리를 골랐다고 가정합시다  
그러면 1번째 자리는 2차원 상태에서 어디인지 다음과 같이 구할 수 있습니다.  
행: 1 / 5 = 0  
열: 1 % 5 = 1  
즉 (0,1)의 자리임을 알 수 있습니다.  
이와 같은 테크닉을 이용해 1차원 배열에서 좌석을 선택하고 2차원 형태로 바꿔서 이다솜파인지 검색합니다!!  
<br>