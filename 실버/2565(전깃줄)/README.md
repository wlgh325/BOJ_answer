# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 2565번 전깃줄 문제 자바(java)  풀이</span>
- 랭크 : 실버1
- [백준 2565번 전깃줄](https://www.acmicpc.net/problem/2565)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 교차하는 전깃줄을 없애서 전깃줄이 교차하지 않도록 하려고 한다.
2. 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄 최소 개수 구하기
3. 전깃줄의 개수는 최대 100개 이다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
전깃줄의 상태를 왼쪽 기준 오름차순으로 나열하면 아래와 같습니다.
```
A 전봇대: 1 2 3 4 6 7 9 10
B 전봇대: 8 2 9 1 4 6 7 10
```
어떻게 하면 제일 조금 자를 수 있을까 생각해보면 B 전봇대의 1,2,3번째 전깃줄을 자르면 3개로 제일 작게 자르는 경우가 됩니다.  
왜나하면 왼쪽수는 이미 증가 상태이기 때문에 오른쪽도 오름차순 이면 전깃줄이 엉키지 않습니다.  
즉 최장 증가 수열(Longest Increasing Sequence)를 구하면 됩니다.  
예에서는 (1 4 6 7 10)이 길이 5로 LIS가 됩니다. 이를 제외하고 3개를 자르면 됩니다.
<br><br>

# <span style="color: blue; font-size:15pt">LIS</span>
DP(Dynamic Programming) 기법을 이용하여 LIS를 구할 수 있습니다.  
dp[x] : x번째 수를 마지막 문자로 가지는 LIS의 길이
1. dp 배열을 1로 초기화 합니다.
2. i번째와 그 보다 왼쪽에 있는 수의 크기를 비교합니다.
3. 비교해서 오른쪽의 값(i번째 수)이 크다면 증가하는 순서로 되어있으므로 dp 배열의 값을 update 합니다.
    이때 현재 i번째 dp의 값 보다 j번째 dp의 값에 1을 더한게 클 경우만 update 해줍니다.
4. 그리고 dp 배열을 돌며 가장 큰 길이의 LIS를 구합니다.
5. 전체 길이에서 LIS 길이 만큼 빼주면 잘라야 하는 최소 전깃줄의 개수가 됩니다.