# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 1764번 집합 c++ 및 java 풀이</span>
- 난이도: 실버4
- [백준 1764 듣보잡](https://www.acmicpc.net/problem/1764)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 듣도 못한 사람의 수 N명과 보도 못한 사람의 수 M명이 있다.
2. 두 명단에 공통인 듣도 보도 못한 사람의 수를 구하고 누구인지 출력하여라.
3. N과 M은 500,000 이하이다.
<br><br>

# <span style="color: red; font-size:15pt">문제 접근</span>
이중 for문을 이용하여 O(NM)으로 하게 되며 250,000,000,000으로 1억당 1초로 잡았을때 2초는 가뿐히 넘기게 되어 시간초과가 납니다.  
그래서 단순 sorting에 list에 포함되어 있는지 contains를 이용해 찾았습니다. 하지만 이 경우도 시간초과....
그래서 sorting은 quicksort, 탐색은 이분탐색을 이용해 합쳐서 푸니까 해결할 수 있었습니다.
<br><br>

# <span style="color: red; font-size:15pt">문제 접근</span>
1. 듣도 못한 사람을 입력 받아 list에 저장합니다.
2. 탐색을 위해 quicksort를 이용하여 정렬합니다.
3. 보도 못한 사람을 입력 받아서 이분탐색을 통해 듣도 못한 사람의 list에 있는지 찾습니다. 찾아서 있다면 듣도 보도 못한 사람의 명단 list에 추가합니다(ans list)
4. ans list의 크기가 듣보잡 사람 수가 됩니다.
5. 정렬은 다시 quickSort를 통해 합니다.
6. 그리고 차례대로 출력합니다.

c++의 경우 sort와 이분탐색은 algorithm에 있는 구현된걸 이용하면 쉽게 풀 수 있습니다.
<br><br>

# <span style="color: red; font-size:15pt">JAVA a.compareTo(b)</span>
1. 값이 0인 경우: 두 string이 같다
2. 값이 0보다 작은 경우: b가 더 앞의 문자이다.
3. 값이 0보다 크다: a가 더 앞에 나온다.
<br><br>