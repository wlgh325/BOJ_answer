# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 10988번 팰린드롬인지 확인하기 자바(java) 풀이</span>
        - 난이도: 브론즈3
        - [백준 10988 팰린드롬인지 확인하기](https://www.acmicpc.net/problem/10988)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
- 문자열이 주어진다
- 팰린드롬인지 확인한다.
- 팰린드롬은 앞으로 읽으나, 거꾸로 읽으나 같은 단어인 경우를 말한다.

# <span style="color: red; font-size:15pt">문제 풀이(Main.java)</span>
1. 문자 각각 확인  
인덱스에 따라서 앞에서 한 문자, 뒤에서 한 문자씩 확인합니다.   
인덱스는 문자열길이/2 전까지만 확인합니다.  
길이가 홀수인 경우에 가운데 글자는 확인하지 않아도 됩니다.