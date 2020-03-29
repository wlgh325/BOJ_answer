# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 18110번 solved.ac 문제 자바(java)  풀이</span>
- 랭크 : 실버4
- [백준 18110번 solved.ac](https://www.acmicpc.net/problem/18110)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 난이도 의견은 문제를 푼 사람들이 생각한 난이도를 의미하는 정수 하나로 주어진다.
2. 아직 아무 의견이 없다면 문제의 난이도는 0으로 결정한다.
3. 의견이 하나 이상 있다면, 문제의 난이도는 모든 사람의 난이도 의견의 30%로 절사평균으로 결정한다.
4. 절사평균: 극단적인 값들이 평균 값을 왜곡하는 것을 방지하기 위해 가장 큰 값들과 가장 작은 값들을 제외하고 평균을 구하는 것!
5. 30% 절사 평균: 위에서 15%, 아래에서 15%를 제외하고 평균을 계산한다.
6. 제외 되는 사람 수는 반올림 해서 결정한다.(25명인 경우 0.15를 곱하면 3.75가 나오는데 반올림하여 4명 절삭)
7. 계산된 평균도 정수로 반올림된다. (절사 평균이 16.7이라면 난이도는 17)
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
1. 위, 아래에서 절사해야할 인원을 구합니다.
    n*0.15를 한 값에 반올림(round)를 한 만큼 절사합니다.
2. 주어진 의견들을 오름차순으로 정렬합니다.(Arrays.sort)
3. 합을 구합니다. 이때 합을 담을 변수는 double 형으로 선언합니다.
    왜냐하면 인원수로 나눌때 둘다 int형이면 결과가 int형으로 나오기 때문입니다.
4. 합을 구한 의견 수는 n-num x 2 입니다. sum 에다가 이 수만큼 나눠주고 round를 취합니다. 그리고 출력합니다.

# <span style="color: blue; font-size:15pt">배열 정렬</span>
그냥 일반 자료형을 담은 배열인 경우 Arrays.sort()를 이용하여 간단하게 정렬할 수 있습니다.
1. 오름 차순 정렬: Arrays.sort(arr)
2. 내림 차순 정렬: Arrays.sort(arr, Collections.reverseOrder());
    sort에 두 번째 인자로 comparator를 추가할 수 있습니다.
    comparator로 Collections.revserOrder()를 줌으로써 역순 즉, 내림차순으로 정렬할 수 있습니다.

# <span style="color: blue; font-size:15pt">반올림</span>
Math.round 함수를 이용하여 반올림 할 수 있습니다.
1. 정수형태로 반올림 : Math.round(num)
2. 소수점 첫 번째 자리로 반올림: Math.round(num*10)/10.0
2. 소수점 첫 번째 자리로 반올림2: 
    ```java
    double num = 34.000
    System.out.println(String.format("%.1f", num))
    // 34.00 출력
    ```
    소수점의 0들 까지 모두 출력하고 싶다면 String.format을 이용해야 합니다. 그렇지 않다면 Math.round를 이용하면 됩니다.