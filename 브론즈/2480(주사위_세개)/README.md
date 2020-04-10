# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 2480번 주사위 세개 자바(java)  풀이</span>
- 랭크 : 브론즈4
- [백준 2480번 주사위 세개](https://www.acmicpc.net/problem/2480)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 주사위 3개를 던져서 다음과 같은 규칭게 따라 상금을 받는 게임이다.
2. 같은 눈이 3개 나오면 1000 + (같은 눈)x1000원의 상금을 받는다
3. 같은 눈이 2개만 나오는 경우 1000 + (같은 눈)*100원의 상금을 받는다
4. 모두 다른 눈이 나오는 경우에는 (그중 가장 큰 눈)*100원의 상금을 받는다
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
단순 if문을 활용하여 같은 값이 몇개있는지 탐색후 풀이하면 됩니다.
1. a,b,c 중 같은 것이 몇개있는지 같은 것이 있다면 어떤 수인지 조건문을 활용해 찾습니다.
2. 그리고 주어진 조건에 맞게 상금을 구합니다.