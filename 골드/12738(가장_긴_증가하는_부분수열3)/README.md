# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 12738번 가장 긴 증가하는 부분수열3 문제 자바(java)  풀이</span>
- 랭크 : 골드2
- [백준 12738번 가장 긴 증가하는 부분수열3](https://www.acmicpc.net/problem/12738)
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
이 문제는 n이 백만으로 크기 때문에 nlogn 알고리즘을 이용해야 합니다.
<br><br>

# <span style="color: blue; font-size:15pt">LIS</span>
0. 수열을 arr 배열에 담습니다. 그리고 LIS를 만들기 위해 lis 배열을 선언합니다.
1. idx는 lis 배열의 위치를 나타내는 포인터입니다.
2. lis 배열의 맨 뒤의 수와 수열의 i번째 수와 비교해서 더 크다면 배열의 맨 뒤에 추가합니다.
3. 만약 작거나 같다면 이분 탐색을 이용해 lower_bound를 찾아 그 위치에 넣습니다.
    만약 lis 배열에 [1,3] 이라는 값이 있었고 '2'라는 값과 비교했다면
    이분 탐색을 이용해 2이상의 값이 처음 나오는 위치인 1을 찾아 그 곳에 2를 넣습니다.
    그러면 [1,2]로 바뀌게 됩니다.
    위와 같은 방법으로 반복해나가며 lis 배열을 완성합니다.
4. 수열의 길이는 idx+1이 되므로 출력합니다.