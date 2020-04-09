# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 1297번 TV 크기 자바(java)  풀이</span>
- 랭크 : 브론즈4
- [백준 1297번 TV 크기](https://www.acmicpc.net/problem/1297)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. TV의 대각선 길이, 높이와 너비의 비율이 주어질때 실제 높이와 너비의 길이를 구하여라
2. 만약, 실제 TV의 높이나 너비가 소수점이 나올 경우에는 그 수보다 작으면서 가장 큰 정수로 출력한다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
이 문제는 **피타고라스의 정리**를 알면 간단히 풀 수 있는 문제입니다.  
피타고라스의 정리를 바탕으로 방정식을 세우고 해를 구하면 됩니다.
```
피타고라스의 정리
c^2 = a^2 + b^2 (단 c는 빗변)
```

빗변은 대각선의 길이가 되고 a와 b는 높이와 너비가 됩니다.  
식을 세우면 다음과 같습니다.  
대각선의 길이^2 = (너비x)^2 + (높이x)^2  정리하면
x = sqrt(대각선의길이^2 / ( (너비x)^2 + (높이x)^2 ))  
이제 x를 구했으니 **너비와 높이에 각각 곱해주면** 너비와 높이를 구할 수 있습니다.  
단!!! 나온 값에 가우스 즉 **floor**를 취하여야 합니다.  
floor를 취한 다음 소수점을 출력하지 않기 위해 **int형으로 typecast**하여 출력합니다.