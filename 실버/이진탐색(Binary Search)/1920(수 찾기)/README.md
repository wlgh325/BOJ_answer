# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 1920번 수 찾기 문제 자바(java)  풀이</span>
- 랭크 : 실버4
- [백준 1920번 수 찾기](https://www.acmicpc.net/problem/1920)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. N개의 정수가 주어집니다.
2. 이때 X라는 정수가 N개의 정수안에 있는지 알아내시오.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
N이 최대 100000이므로 선형 탐색으로는 시간내에 불가할 것 같다고 생각하였습니다.  
N이 5000보다 크고 10^6보다는 작으므로 O(logN)에서 O(N안에 풀어야 합니다.  
그러기 위해서 **이진 탐색**을 이용합니다.
<br><br>

# <span style="color: red; font-size:15pt">이진 탐색</span>
1. 초기의 left값은 0(왼쪽 끝 인덱스), right 값은 배열의 오른쪽 끝 인덱스입니다.
2. left가 right보다 커질때 까지 반복합니다.
    2.1 (left+right)/2를 계산하여 중간 index(mid)를 찾습니다.
    2.2 찾으려는 값과 배열의 mid의 값이 같으면 그 인덱스를 반환합니다.
    2.3 찾으려는 값이 배열의 mid 위치의 값보다 작으면 mid보다 왼쪽에 있음을 뜻합니다. 그렇기 때문에 왼쪽에서만 찾기 위해서 right 인덱스를 mid보다 1작게 합니다.
    2.4 찾으려는 값이 배열의 mid 위치의 값보다 크면 mid보다 오른쪽에 있음을 뜻합니다. 그렇기 떄문에 오른쪽에서만 찾기 위해서 left index를 mid보다 1크게 합니다.
3. 찾지 못하고 while문을 나오게 되면 -1을 반환합니다.