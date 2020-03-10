# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 9012번 괄호 문제 자바(java)  풀이</span>
- 랭크 : 실버4
- 풀이시간: 약 20분
- 메모리: 13108 KB
- 실행시간 : 84 ms
- [백준 9012번 괄호](https://www.acmicpc.net/problem/9012)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 괄호 만으로 이루어진 괄호 문자열이 있다.
2. 괄호의 모양이 쌍을 이루어 올바르게 구성된 경우 올바른 괄호 문자열(VPS)이라고 한다.
3. x가 VPS라면 (x)도 VPS이다.
4. x와 y가 VPS라면 xy도 VPS이다.
5. 입력으로 주어진 괄호 문자열이 VPS인지 아닌지 판단해서 YES, NO로 출력해라
6. 문자열의 길이는 2이상 50이하이다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
스택을 이용해 올바른 괄호인지 검사합니다. 스택의 기본문제라고 할 수 있습니다.  
괄호의 짝이 맞는지 맞지 않는지 검사하는 문제입니다.
1. '(' 문자라면 stack에 넣습니다.
2. ')' 문자라면 stack이 비어있지 않는 경우에 넣고 비어있다면 ')' 문자가 하나 더 나온 것이므로 flag를 true로 하여 바로 for문을 빠져 나가고 'NO'를 출력합니다.
3. '('와 ')'의 개수가 맞아서 끝까지 탐색하였다면 탐색한 후에 stack의 크기를 확인합니다.
    stack의 크기가 0이라면 짝을 잘 맞은 것이므로 'YES'를 출력합니다.
    stack의 크기가 1이라면 짝이 맞지 않아 stack에 남은 것이므로 'NO'를 출력합니다.