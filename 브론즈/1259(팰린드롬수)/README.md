# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 1259번 팰린드롬수 문제 자바(java)  풀이</span>
- 난이도: 브론즈1
- [백준 1259번 팰린드롬수](https://www.acmicpc.net/problem/1259)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 팰린드롬수는 앞으로 읽거나 거꾸로 읽거나 같은 수를 말한다.
2. 숫자가 주어졌을때 그 수가 팰린드롬수인지 아닌지 판별하여라
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
1. 숫자를 문자형태로 입력받는다.
2. 0이면 더 이상 입력받지 않는다.
3. 길이를 구해서 절반 만큼만 확인한다.
    맨 앞 글자, 맨 뒷 글자
    두 번째 글자, 뒤에서 두 번째 글자.. 이런식으로 같은지 확인해 나간다.
    확인해서 같으면 넘어가고 아니면 flag를 false로 만든다.
4. flag가 false면 팰린드롬수가 아니므로 no, 반대면 yes를 출력한다.
<br><Br>