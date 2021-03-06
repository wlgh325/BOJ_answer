# BOJ 6568번 귀도 반 로썸은 크리스마스날 심심하다고 파이썬을 만들었다 문제 자바(java)  풀이
- 랭크 : 골드5
- 백준 온라인 저지(BOJ) 6568번 귀도 반 로썸은 크리스마스날 심심하다고 파이썬을 만들었다 문제 자바 풀이
- [백준 6568번 거울 설치](https://www.acmicpc.net/problem/6568)

## [티스토리 주소](https://hoho325.tistory.com/82?category=780777)

# 문제정리
1. 각 명령어의 길이는 1바이트
2. 상위 3비트는 명령어, 하위 5비트는 피연산자
3. 피 연산자는 언제나 메모리 값
4. 피 연산자가 필요하지 않는 명령도 있다. 이때 하위 5비트는 무의미
5. 명령어를 실행하기 전에 pc값을 1 증가시킨다.

# 키 포인트
1. 입력으로 주어진 이진수 형태의 문자를 십진수 정수로 변환
2. 정답 출력시 십진수 정수형을 이진수 형태의 문자로 변환
3. 비트 연산을 통해 명령어와 주소 분리
4. 테스트 케이스가 한 셋트가 아닌 여러 셋트가 입력으로 주어질 수 있다.
5. 테스트 케이스가 끝나는 것은 EOF로 처리한다.
6. ac에서 1을 뺄때 overflow가 나는 것을 처리한다. (안해도 되나??)

# 문제풀이
1. while문을 돌며 32개의 명령어를 입력으로 받는다.
2. 32개를 다 받으면 프로그램 명령어들을 처리한다.
3. 명령어와 주소를 분리하여 그에 맞는 명령을 처리한다. 
4. 프로그램이 끝나면 결과를 이진수 형태로 바꾸어 출력한다.
5. 변수들을 초기화 하고 다시 다음 테스트 케이스가 있다면 입력을 받아 위의 과정을 반복한다.

* * *
* * *

# bit 연산
1. bit shift 연산
```
'>>'을 통해 bit를 오른쪽으로 움직여 명령어를 뽑아낸다
예를들어 11100001 >> 5 연산을 하면 오른쪽으로 5 bit shift하여 00000111이 된다.
```

2. & 연산
```
이는 && 연산과 다르다 &&는 bit 연산이 아닌 and 연산이다
&는 bit and 연산이다. 두 값이 모두 1인 경우에만 1이된다
이는 특정 부분의 숫자만 뽑아내기 위해서 자주 사용한다.
예를 들어 10101111이라는 숫자에 0x31을 & 연산하면

 10101111
&00011111
=00001111

즉 맨 왼쪽 4자리만 뽑아내 주소만 뽑아낼 수 있다.
```

# 10진수 변환
```
2진수를 10진수로 변환하는 일반적인 방법 그대로르 사용하였다.
예를 들어 2진수 1001이 있다면 이는 1*2^3 + 0*2^2 + 0*2^1 + 1*2^0 = 9가 된다
이를 string으로 주어졌을 때 한 자리씩 끊어서 계산하였다.
```

# 2진수 변환
```
이도 일반적으로 사용하는 10진수->2진수 변환 법을 사용하였다
십진수를 2로 나눈 나머지를 가지고 하는 방법을 이용하였다.
10 2 | 0
5  2 | 1
2  2 | 0
1  2 | 1

=> 10=1010이 된다
이 방법에 8비트로 만들기 위해서 나머지 앞에 부분은 0으로 채워주었다.
```

# 음수 표현
```
프로그램을 돌리다 보니 가산기에 음수 값이 들어가는 경우가 있었다.
만약 음수인 상태로 프로그램이 종료 된다면 출력이 이상하게 될 것이다.
그래서 음수인 경우 2의 보수를 취하여 바꿔주었다.
예를 들어 -1이 있다면 '-'를 떼고 1만 본다
그러면 00000001이다. 여기에 1의 보수를 취하면 11111110이 되고
1을 더해주면 2의 보수인 11111111이 된다. 이게 바로 -1을 나타낸 것이다.
```