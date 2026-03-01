# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 11478번 서로 다른 부분 문자열의 개수 자바(java) 풀이</span>
        - 난이도: 실버3
        - [백준 11478 서로 다른 부분 문자열의 개수](https://www.acmicpc.net/problem/11478)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
- 문자열이 주어진다
- 서로 다른 부분 문자열을 확인해서 개수를 센다

# <span style="color: red; font-size:15pt">문제 풀이(Main.java)</span>
1. substring   
시작 인덱스를 늘려가면서 길이만큼 부분 문자열을 구한다.  
중복되는 부분 문자열을 제거하기 위해 set에 저장하여 크기를 반환한다.  
이렇게 하면 substring(0,0)으로 공백이 포함되므로 크기를 1개 뺀다.