# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 5430번 AC 문제 자바(java)  풀이</span>
- 랭크 : 실버2
- [백준 5430번 AC](https://www.acmicpc.net/problem/5430)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. AC에는 정수 배열을 위한 두 가지 함수가 있다.
    R(뒤집기) : 배열에 있는 숫자 순서를 뒤집는다.
    D(버리기) : 첫 번째 숫자를 버린다.
2. RDD 처럼 함수를 바로 이어서 사용할 수 있다. 한 번 뒤집은 다음 두개의 숫자를 버린다.
3. 함수 실행 후, 최종 결과를 구하여라
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
수를 실제로 뒤집고 반복한다면 시간초과가 나게 될것입니다.  
그러므로 deque를 이용하여 앞 뒤에서 숫자를 지워줍니다.
1. 파싱하는 것이 가장 중요합니다. 숫자는 한자리수가 아니라 최대 100,000임을 인지해야 합니다.
    이것 때문에 런타임 에러를 많이 봤습니다..ㅠㅠ
2. 배열에 주어진 수가 하나도 없을때 함수에 D가 포함되어 있다면 에러를 표시합니다.
3. 그렇지 않다면 '['와 ']'를 잘라내고 ','를 기준으로 parsing하여 deque에 넣습니다.
4. 그리고 R에 따라서 수를 앞, 뒤로 뺍니다. 만약에 빼려는데 deque가 비었다면 error를 출력합니다.
5. error 없이 정상적으로 모두 실행했다면 출력형식에 맞게 deque에서 꺼내서 출력합니다.