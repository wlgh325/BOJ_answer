# BOJ 8320번 직사각형을 만드는 방법 문제 자바(java)  풀이
- 랭크 : 브론즈 3
- 백준 온라인 저지(BOJ) 8320번 직사각형을 만드는 방법 문제 자바 풀이
- [백준 8320 직사각형을 만드는 방법](https://www.acmicpc.net/problem/8320)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 변의 길이가 1인 정사각형 n개를 가지고 만들 수 있는 직사각형의 개수를 구하자  
2. 회전, 이동시켜 같은 경우 두 직사각형은 같다고 본다.  
3. 정사각형을 변형시키거나, 정사각형 위에 다른 정사각형을 놓으면 안된다.
4. 직사각형은 정사각형으로 가득차야 한다.

# 문제풀이
이 문제를 어떻게 풀어야 할까 20분(?) 정도 고민한 것 같다.  
모든 경우를 어떻게 찾야아 할까 고민하다가 규칙을 발견하였다.
예를들어 정사각형 4개를 가지고 직사각형을 만든다면 일렬로 나열하는 경우 1가지  
**반으로 나눠서** 밑에 붙여서 만드는 경우 한가지 해서 총 2가지 이다.  
여기서 아이디어를 착안하였다!!
```
1: 1 (1)
2: 1 (1,2)
3: 1 (1,3)
4: 2 (1,2,4)
5: 1 (1,5)
6: 2 (1,2,3,6)
7: 1 (1,7)
8: 2 (1,2,4,8)
9: 2 (1,3,9)
10: 2 (1,2,5,10)
11: 1 (1,11)
12: 3 (1,2,3,4,6,12)
```
약수들을 구해보면 위와 같다.
여기서 개수와 비교해보면 약수의 개수가 홀수인 경우 2로 나눈 수의 + 1 개이고(1은 예외)  
짝수인 경우는 약수의 개수/2 개 이다. 
이렇게 약수들의 개수를 구하여 총 만들 수 있는 직사각형의 합을 구하였다.