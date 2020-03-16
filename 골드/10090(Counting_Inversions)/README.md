# <span style="color:orange; font-size:17pt; font-weight:bold"></span>BOJ 10090번 Counting Inversions 문제 자바(java)  풀이</span>
- 랭크 : 골드3
- [백준 10090번 Counting Inversions](https://www.acmicpc.net/problem/10090)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 숫자 배열이 주어진다.
2. 앞의 숫자보다 뒤의 숫자가 더 크다면 Inversion이 일어납니다.
3. 이 Inversion이 일어나는 횟수를 셉니다.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
1. merge sort 방식을 이용하여 왼쪽과 오른쪽의 숫자를 비교할때 왼쪽의 숫자가 더 작다면 그 개수를 구합니다.
    오름 차순이므로 왼쪽의 수가 더 크다면 그 뒤의 숫자들 모두 오른쪽의 수 보다 큽니다. 이를 이용하여 Inversion의 개수를 구할 수 있습니다.
2. inversion의 개수는 long형으로 선언합니다.