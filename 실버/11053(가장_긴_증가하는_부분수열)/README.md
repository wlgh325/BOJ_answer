# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 11053번 가장 긴 증가하는 부분수열 자바(java)  풀이</span>
- 랭크 : 실버2
- [백준 11053번 가장 긴 증가하는 부분수열](https://www.acmicpc.net/problem/11053)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 수열이 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하여라.
2. A = {10, 20, 10, 30, 20, 50}이라면 답은 {10, 20, 30, 50}이고 길이는 4이다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
최장 증가 수열(Longest Increasing Sequence)을 구하는 문제입니다.  
수열의 부분 수열 중에 증가하는 순으로 가장 긴 수열을 찾아내면 됩니다.  
LIS를 구하는 방법에 O(n^2) 방법과 O(nlogn) 방법이 있습니다.  
- dp방법: O(n^2)이기 때문에 n이 최대 100,000이기 때문에 이 방법으로 풀 수 없습니다
- segment tree: O(nlogn) 방법으로 n이 10만이기 때문에 이 방법을 이용합니다.
- 이분 탐색: O(nlogn)으로 segment tree와 시간 복잡도가 같습니다.
이 문제는 n이 1000으로 작기 때문에 O(n^2)으로도 충분히 풀 수 있습니다.
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