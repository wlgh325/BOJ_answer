# <span style="color: orange; font-weight:bold; font-size:17pt">BOJ 2580번 스도쿠 자바(java)  풀이</span>
- 난이도: 골드4
- [BOJ 2580번 스도쿠](https://www.acmicpc.net/problem/2580)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)

# <span style="color: red; font-weight:bold; font-size:15pt">문제 정리</span>
1. 9x9 판으로 이루어진 판에 1~9 까지의 숫자들이 써있다.
2. 나머지 빈 칸을 채우는 방식은 다음과 같다.
  - 각각의 가로줄과 세로줄에는 1~9까지의 숫자가 한 번씩만 나타난다.
  - 굵은 선으로 구분되어 있는 3x3 정사각형 안에도 1~9 까지의 숫자가 한 번씩만 나타난다.
3. 모든 빈 칸이 채워진 최종 모습을 출력하는 프로그램을 작성하시오.
4. 스도쿠 판을 채우는 방법이 여럿인 경우 그 중 하나만을 출력한다.
<br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제 풀이</span>
n-queen 문제와 유사한 방식으로 백트래킹을 이용하여 문제를 해결합니다.  
백트래킹으로 가능한 모든 경우를 탐색하면 됩니다.
1. 완전 naive하게 값을 놓으려는 위치에 해당하는 행, 열, 박스를 모두 검사합니다.
2. 각 행, 열, 박스의 숫자가 쓰여져있는지 여부를 판단하느 boolean type의 visited 배열 형태를 만들어서 값을 쓸 수 있는지 아닌지 계산한다.

2번 방법으로 문제풀이를 시도하다가 박스의 인덱스를 어떤 수식으로 해야할지 감이 안와서 1번 방법으로 naive하게 풀이 하였습니다.
1. isValid 함수를 통해 행, 열, 박스안에 같은 값이 있는지 판단하여 없다면 sdoku 배열에 값을 써넣습니다.
  isValid 함수는 if문을 중첩하여 모든 경우의 수를 나누어 검사하였습니다.
2. 그리고 다음에 놓을 위치를 list에서 꺼내서 재귀적으로 호출합니다.
3. cnt 값이 list의 크기보다 1 작은 값이라면 이제 마지막 빈 칸에 값을 쓸 차례입니다. 마지막 칸에 값을 쓸 수 있는지 검사하여 씁니다.
  만약에 값을 쓸 수 없다면 그 자리의 값은 0이 되므로 return 하여 다른 경우의 수를 찾아봅니다.
  그렇지 않다면 스도쿠 배열을 출력하고 프로그램을 종료합니다.
<br>

* 박스의 인덱스 참조: ((i-1) / 3) * 3 + ((j-1) / 3) => (i,j) 번째가 위치한 박스 번호
왼쪽 위 부터 차례대로 0,1,2 .. 8번