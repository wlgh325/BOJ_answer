# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 1158번 요세푸스 문제1 자바(java)  풀이</span>
- 랭크 : 실버5
- 풀이시간: 5분
- 실행시간: 584ms
- 메모리: 277724 KB
- [백준 1158번 요세푸스 문제1](https://www.acmicpc.net/problem/1158)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 1~N번까지 N명의 사람이 원 모양으로 앉아있다.
2. k가 주어질때 k번째 사람을 제거한다.
3. 한 사람이 제거되면 남은 사람들로 이루어진 원에서 2번을 반복한다.
4. N명의 사람이 모두 제거될 때까지 계속된다.
5. 사람들이 제거 되는 순서를 출력해라.
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
이 문제는 [요세푸스 문제0](https://www.acmicpc.net/problem/11866)번과 똑같이 제출해도 통과할 수 있습니다.  
즉 naive하게 문제에서 주어진대로 구현하면 통과할 수 있습니다.  
하지만 이 문제는 queue를 이용하여 작성해보았습니다.  
queue에서 뺄 필요 없이 ArrayList를 이용하면 더 빠릅니다.
1. 1~N까지 queue에 모두 넣습니다.
2. k-1번째 까지 숫자를 빼서 뒤에 차례로 넣습니다.
3. k번째 사람을 제거하고 출력합니다.
4. 2,3번을 queue에 1개가 남을때까지 반복합니다.
5. 마지막 element를 출력형식에 맞게 출력합니다.