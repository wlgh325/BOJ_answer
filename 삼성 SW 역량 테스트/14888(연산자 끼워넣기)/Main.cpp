#include <iostream>
using namespace std;

int N;
int arr[11];
int oper[4];
int maxx;
int minn;

void dfs(int idx, int res) {
	// 모든 연산을 끝낸 경우
	// maxx, min 값 업데이트
	if (idx == N) {
		maxx = maxx < res ? res : maxx;
		minn = minn > res ? res : minn;
		return;
	}
	
	// 남아있는 연산자 check
	for (int i = 0; i < 4; i++) {
		// 사용하지 않은 연산자가 남아 있다면 그 연산 수행
		if (oper[i] != 0) {
			oper[i]--;	// 연산자 사용
			switch (i) {
				case 0:
					dfs(idx + 1, res + arr[idx]);
					break;
				case 1:
					dfs(idx + 1, res - arr[idx]);
					break;
				case 2:
					dfs(idx + 1, res * arr[idx]);
					break;
				case 3:
					dfs(idx + 1, (int)(res / arr[idx]));
					break;
			}
			oper[i]++;	// 원복
		}
	}
}

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	// 식의 계산은 앞에서 뒤로 차례대로
	// 나눗셈은 몫만 취한다.
	// 음수를 양수로 나눌 때는 c++14 기준을 따른다 (양수로 바꾼 뒤 몫을 취하고 몫을 음수로 바꾼 것과 같다)
	// 만들 수 있는 식의 결과의 최대 최소 값을 구하여라

	// 숫자 들
	cin >> N;
	for (int i = 0; i < N; i++)
		cin >> arr[i];
	
	// 연산자 개수
	for (int i = 0; i < 4; i++)
		cin >> oper[i];

	maxx = -1e9;
	minn = 1e9;

	dfs(1, arr[0]); // 백트래킹

	cout << maxx << "\n";
	cout << minn << "\n";
	return 0;
}