# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 10815번 숫자 카드 문제 자바(java)  풀이</span>
- 랭크 : 실버4
- 풀이시간: 15분
- 메모리: 159520 KB
- 시간: 1772 ms
- [백준 10815번 숫자 카드](https://www.acmicpc.net/problem/10815)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 숫자 카드에는 정수 하나가 적혀있다.
2. 상근이는 숫자 카드 N개를 가지고 있다.
3. 정수 M개가 주어졌을 때, 이 수가 적혀있는 카드를 상근이가 가지고 있는지 아닌지 구하여라.
4. N은 최대 500,000이고 숫자 카드에 적혀 있는 숫자는 최소 -천만, 최대 +천만 이다.
5. M의 최대는 N과 같고 구해야할 숫자도 숫자 카드에 적혀있는 숫자 범위와 같다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
m개에 대해서 n번 탐색하게 되면 최대 2천5백억번을 탐색해야 합니다. 즉 단순하 탐색으로는 찾을 수 없습니다.  
딱 생각난 풀이는 이진탐색이었습니다. 이진탐색을 구현하여 찾아야 할 수를 logn 시간에 찾습니다.
<br><br>

# <span style="color: red; font-size:15pt">이진 탐색</span>
1. 초기의 left값은 0(왼쪽 끝 인덱스), right 값은 배열의 오른쪽 끝 인덱스입니다.
2. left가 right보다 커질때 까지 반복합니다.
    2.1 (left+right)/2를 계산하여 중간 index(mid)를 찾습니다.
    2.2 찾으려는 값과 배열의 mid의 값이 같으면 그 인덱스를 반환합니다.
    2.3 찾으려는 값이 배열의 mid 위치의 값보다 작으면 mid보다 왼쪽에 있음을 뜻합니다. 그렇기 때문에 왼쪽에서만 찾기 위해서 right 인덱스를 mid보다 1작게 합니다.
    2.4 찾으려는 값이 배열의 mid 위치의 값보다 크면 mid보다 오른쪽에 있음을 뜻합니다. 그렇기 떄문에 오른쪽에서만 찾기 위해서 left index를 mid보다 1크게 합니다.
3. 찾지 못하고 while문을 나오게 되면 -1을 반환합니다.