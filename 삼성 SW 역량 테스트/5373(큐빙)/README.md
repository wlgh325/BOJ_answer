# <span style="color:orange; font-size:17pt; font-weight:bold">BOJ 5373번 큐빙 문제 자바(java)  풀이</span>
- 랭크 : 골드1
- [백준 5373번 큐빙](https://www.acmicpc.net/problem/5373)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-size:15pt">문제 정리</span>
1. 윗면: 흰색, 아랫면: <span style="color:yellow">노란색</span>, 앞면: <span style="color:red">빨간색</span>, 뒷면: <span style="color:orange">오렌지색</span>, 왼쪽 면: <span style="color:green">초록색</span>, 오른쪽면: <span style="color:blue">파란색</span>
2. 루빅스 큐브를 돌린 방법이 순서대로 주어진다. 이때, 모두 돌린 다음에 가장 윗 면의 색상을 구하는 프로그램을 작성하시오
3. 테스트 케이스의 개수가 주어지며 최대 100개이다
4. 큐브를 돌린 횟수 n이 주어진다.
5. 큐브를 돌린 방법이 주어진다. 방법은 공백으로 구분되어 있다. (돌린면(UDFBLR)돌린방향(+-)). 돌린 방향은 그 방향을 바라봤을 때가 기준이다.
6. 첫째줄에는 뒷면과 접하는 줄의 색깔, 두 번째, 세 번째줄의 색을 출력한다. (w, y, r, o, g, b)
<br><br>

# <span style="color: red; font-size:15pt">문제 풀이</span>
정말 어려운 구현 문제였습니다. 코드 한 부분이라도 잘못되면 답이 제대로 나오지 않습니다.  
각 회전을 모두 따로 구현해주어야 해서 쉽지 않았습니다.
총 12가지의 회전을 구현해야 합니다.
0. 우선 큐브의 상태를 다음과 같이 표현하였습니다.
```
   ooo
   ooo
   ooo
gggwwwbbbyyy
gggwwwbbbyyy
gggwwwbbbyyy
   rrr
   rrr
   rrr
```
   9x12 배열을 잡아서 표현하였습니다. 아무것도 없는 부분은 6의 값을 넣어주었고 w,y,r,o,g,b 순으로 0~5까지의 값을 할당하였습니다.
1. U+ : 윗면이 돌아가면 그와 붙어 있는 부분도 같이 돌아가게 됩니다. 그래서 5x5크기로 잡아서 모두 돌려주었습니다.
초기상태에서 돌린다면 가운데에서 상하좌우로 한 칸씩 더 잡아서 한 번에 돌렸습니다. 회전을 위해 원본 배열을 복사해두고 회전하였습니다.
```
 ooo
gwwwb
gwwwb
gwwwb
 rrr
```
<br><br>

2. U-: 반시계 방향 회전은 시계방향으로 3번 회전한 것과 같습니다. 이를 이용하였습니다.  

여기서 부터는 **rotateClockwise** 함수를 이용하였습니다.
이 함수는 회전을 위해 배열을 복사하고 마찬가지로 인덱스를 이용하여 회전시킵니다.
다른 면의 회전에도 적용시키기 위하여 일반화하였습니다.
- rstart, rend: 회전시킬면이 포함된 row의 인덱스를 나타냅니다. 예를들어 왼쪽면을 회전시키려 한다면 왼쪽면은 3~5줄에 있으므로 rstart=3, rend=6이 됩니다.
- cstart, cend: 회전시킬면이 포함된 col의 인덱스를 나타냅니다. 왼쪽면을 회전시킨다면 왼쪽면은 0,1,2에 걸쳐 있으므로 cstart=0, cend=3이 됩니다.
<br><br>

3. D+: 우선 뒷면을 rotateClockwise 함수를 이용하여 회전시킵니다. 그리고 그와 연결된 부분들도 돌아가게 됩니다. 기본 상태에서 회전시키면 아래와 같이 바뀌게 됩니다. 이를 rotate_dc 함수를 이용하여 구현하였습니다.
```
   yyy
   ooo
   ooo
oggwwwbbbyyr
oggwwwbbbyyr
oggwwwbbbyyr
   rrr
   rrr
   ggg
```
<br><br>

4. D-: 이도 D+를 3번하는 것과 같습니다. rotate_dc는 반시계 방향 회전으로 구현하여서 D+의 경우 3번, D-의 경우 한 번 시행합니다.
<br><br>

5. F+: rotateClockwise 함수를 이용하여 앞 면을 회전 시키고 그에 붙은 면들도 회전하게 됩니다. 이는 rshitf 함수를 이용해 구현하였습니다.
```
   ooo
   ooo
   ooo
gggwwwbbbyyy
gggwwwbbbyyy
yyygggwwwbbb
   rrr
   rrr
   rrr
```
위와 같이 5번째 줄이 오른쪽으로 한 번 회전에 3칸씩 shift 됩니다. 이 함수를 뒷 면 회전시에도 사용하기 위해 row의 index를 받아 사용할 수 있도록 일반화 하였습니다.
한 칸씩 shift 시키기 위해서 queue를 이용하였습니다.
queue에 담고 하나씩 꺼내며 한 칸씩 밀린 index에 담아주면 됩니다.
<br><br>

6. F-: 이는 F+를 3번한것과 같습니다.
<br><br>

7. B+: rotateClockwise를 이용하여 뒷 면을 회전시킵니다. 뒷 면 회전시 세번째 row가 왼쪽으로 3칸밀리게 됩니다. 이를 rshift 함수를 3번 호출하여 왼쪽으로 움직인 것처럼 하였습니다.
<br><br>

8. B-: 이도 rotateClockwise를 3번 한것과 같습니다.
<br><br>

9. L+: 왼쪽 면을 회전시킵니다. 회전시 3번째 row가 아래쪽으로 3칸씩 밀리게 됩니다.
```
   yoo
   yoo
   yoo
gggowwbbbyyr
gggowwbbbyyr
gggowwbbbyyr
   wrr
   wrr
   wrr
```
위와 같이 변하게 됩니다. 이때 밑에 면이 뒤쪽으로 올라오게 됩니다. 이도 queue를 이용하여 구현하였습니다.

10. L-: L+를 3번 반복하였습니다.
<br><br>

11. R+: 우선 오른쪽 면을 회전 시킵니다. 이도 왼쪽면때와 마찬가지로 붙어 있는 면이 위쪽으로 shift 됩니다.
```
   oow
   oow
   oow
gggwwrbbboyy
gggwwrbbboyy
gggwwrbbboyy
   rry
   rry
   rry
```
위와 같이 바뀌는 것을 볼 수 있습니다. 주의할 점은 밑 바닥 면도 바뀐다는 것입니다.
12. R-: r-를 3번 반복합니다.
