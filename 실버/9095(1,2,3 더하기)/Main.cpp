#include <iostream>
using namespace std;

int T, N, ans;
vector<int> v, idx;

void add(int n) {
	if (n < 0)	return;

	if (n == 0) {
		ans++;
		return;
	}
	add(n - 1);
	add(n - 2);
	add(n - 3);
}

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> T;
	for (int i = 0; i < T; i++) {
		cin >> N;
		add(N -1);
		add(N - 2);
		add(N - 3);
		cout << ans << endl;
		ans = 0;
	}
	return 0;
}