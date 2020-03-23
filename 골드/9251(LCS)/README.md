# <span style="color: orange; font-weight:bold; font-size:17pt">BOJ 9251번 LCS 자바(java)  풀이</span>
- 난이도: 골드5
- [BOJ 9251번 LCS](https://www.acmicpc.net/problem/9251)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)

# <span style="color: red; font-weight:bold; font-size:15pt">문제정리</span>
1. 두 수열이 주어졌을때 모두의 부분 수열이 되는 수열 중 가장 긴 것(LCS)을 찾아라
2. 문자는 대문자로만 이루어지며 최대 1000글자
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제 풀이</span>
이 문제는 문제 이름이 그렇듯이 LCS(Longest Common Subsequence) 알고리즘을 이용해 풀 수 있습니다.  
subsequence는 보통 알고 있는 substring하고는 다릅니다.
- subsequence: 연속되지 않은 부분 문자열
- substring: 연속된 부분 문자열
123456과 145679 두개의 문자열을 가지고 보겠습니다.  
- 두 문자열의 가장 긴 substring: 56
- 두 문자열의 가장 긴 subsequence: 156

