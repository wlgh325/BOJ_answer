# <span style="color: orange; font-weight:bold; font-size:17pt">BOJ 9251번 LCS 자바(java)  풀이</span>
- 난이도: 골드5
- [BOJ 9251번 LCS](https://www.acmicpc.net/problem/9251)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)

# <span style="color: red; font-weight:bold; font-size:15pt">문제정리</span>
1. 두 수열이 주어졌을때 모두의 부분 수열이 되는 수열 중 가장 긴 것(LCS)을 찾아라
2. 문자는 대문자로만 이루어지며 최대 1000글자
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">LCS</span>
이 문제는 문제 이름이 그렇듯이 LCS(Longest Common Subsequence) 알고리즘을 이용해 풀 수 있습니다.  
subsequence는 보통 알고 있는 substring하고는 다릅니다.
- subsequence: 연속되지 않은 부분 문자열
- substring: 연속된 부분 문자열
123456과 145679 두개의 문자열을 가지고 보겠습니다.  
- 두 문자열의 가장 긴 substring: 56
- 두 문자열의 가장 긴 subsequence: 156

# <span style="color: red; font-weight:bold; font-size:15pt">LCS 길이 구하기</span>
dp 기법을 이용하여 풉니다. 우선 계산을 편하게 하기 위하여 맨 윗줄과 맨 왼쪽줄을 0으로 추가합니다.  
CAPCAK, ACAYKP 두 문자의 LCS를 구하는 것을 예시로 보여드리겠습니다.
우선 LCS를 모두 탐색한뒤 dp 배열은 다음과 같이 됩니다.
```
    C A P C A K
  0 0 0 0 0 0 0 
A 0 0 1 1 1 1 1
C 0 1 1 1 2 2 2
A 0 1 2 2 2 3 3
Y 0 1 2 2 2 3 3
K 0 1 2 2 2 3 4
P 0 1 2 3 3 3 4
```
1. A와 C를 비교합니다. 같지 않기 때문에 0을 씁니다.
2. A와 C'A'를 비교합니다. A가 같기 떄문에 1을 씁니다.
3. A와 CA'P'를 비교합니다. 같지 않습니다. 전에 CA까지 같았기 때문에 CAP까지 비교했을때 LCS는 'A'로 길이 1입니다.
4. A와 CAP'C'를 비교합니다. 같지 않기 때문에 이전까지 최대 길이인 1을 씁니다.
5. A와 CAPC'A'를 비교합니다. 같습니다. 이번에도 'A'로 LSC 길이는 1입니때.
6. A와 CAPCA'K'를 비교합니다. 다릅니다. 이전까지의 LCS 길이인 1을 씁니다.

이제 첫 문자 A와의 비교를 마쳤습니다. 다음 문자인 C까지 비교해보겠습니다.
1. C와 C를 비교합니다. 1을 씁니다.
2. C와 C'A'를 비교합니다. 다릅니다. 이때 A와 CA를 비교했을때의 LCS 길이와 AC와 C를 비교했을 때의 길이를 비교하여 더 큰쪽에서 값을 물려받습니다.
    AC와 CA를 비교하는 부분입니다. 이미 A와 CA는 비교했기 때문에 이전까지 비교한 정보를 가져다 쓰는 것입니다.
3. C와 CA'P'를 비교합니다. 다르기 때문에 A와 CA를 비교했을떄의 LCS길이와 AC와 CA를 비교했을때의 길이 중 더 큰 값을 씁니다.
4. C와 CAP'C'를 비교합니다. 같기 때문에 AC와 CAP를 비교했을때의 LCS 값에 1을 더해 써줍니다. 왜냐하면 AC와 CAP를 비교했을때 최대 길이가 1이었으므로 거기에 같은 문자 1이 추가된 것이므로 2가 됩니다.
5. C와 CAPC'A'를 비교합니다. 다르기 때문에 3번과 같이 더 큰값을 가져옵니다. 이때 AC와 CAPC 까지 비교했을때 LCS 길이가 2로 A와 CAPCA를 비교했을때 보다 크기 때문에 이 값을 가져와 씁니다.
6. C와 CAPCA'K'를 비교합니다. 같지 않기 때문에 AC와 CAPCA를 비교했을때의 길이 2를 가져다 씁니다.

위와 같은 방식으로 계속 진행합니다. 그러면 dp[len2][len1]에 쓰여진 4라는 값이 LCS의 길이가 됩니다.