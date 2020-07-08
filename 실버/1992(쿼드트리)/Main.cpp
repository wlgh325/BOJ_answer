#include <iostream>
#include <string>

#define endl '\n'
using namespace std;

int N;
string ans;
int map[64][64];

bool check(int startX, int endX, int startY, int endY) {
	int color = map[startY][startX];

	for (int i = startY; i < endY; i++) {
		for (int j = startX; j < endX; j++) {
			if (color != map[i][j]) return false;
		}
	}
	return true;
}

void make(int startX, int endX, int startY, int endY, int len) {
    // 압축되어 있는지 확인
	if (!check(startX, endX, startY, endY)) {
		ans += "(";
		make(startX, startX + len / 2, startY, startY + len / 2, len / 2);  // 왼쪽 위
		make(startX + len / 2, startX + len, startY, startY + len / 2, len / 2);    // 오른쪽 위
		make(startX, startX + len / 2, startY + len / 2, startY + len, len / 2);    // 왼쪽 아래
		make(startX + len / 2, startX + len, startY + len / 2, startY + len, len / 2);  // 오른쪽 아래
		ans += ")";
	}
	else {
		ans += to_string(map[startY][startX]);
	}
}

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N;

	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		for (int j = 0; j < N; j++) {
			map[i][j] = s[j] - '0';
		}
	}

	// 조건 만족하는지 확인
	if (check(0, N, 0, N)) {
		if (map[0][0] == 0) cout << 0 << endl;
		else cout << 1 << endl;
	}
	else {
		ans += "(";
		make(0, N / 2, 0, N / 2, N / 2);    // 왼쪽 위
		make(N / 2, N, 0, N / 2, N / 2);    // 오른쪽 위
		make(0, N / 2, N / 2, N, N / 2);    // 왼쪽 아래
		make(N / 2, N, N / 2, N, N / 2);    // 오른쪽 아래
		ans += ")";
	}

	cout << ans << endl;
	return 0;
}