#include <iostream>
#include <utility>	// pair 사용
#include <algorithm>	// min 사용
using namespace std;

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	int N;
	cin >> N;

	int cnt[501];
	pair<int, int>fact[501];

	cnt[0] = 0;
	cnt[1] = 0;
	for (int i = 2; i <= N; i++) {
		int temp = i;
		int two = 0;
		int five = 0;

		while (1) {
			if (temp % 2 == 0) {
				two++;
				temp /= 2;
			}
			else
				break;
		}
		temp = i;

		while (1) {
			if (temp % 5 == 0) {
				five++;
				temp /= 5;
			}
			else
				break;
		}

		fact[i].first = fact[i - 1].first + two;
		fact[i].second = fact[i - 1].second + five;
		cnt[i] = min(fact[i].first, fact[i].second);
	}
	cout << cnt[N] << endl;
	return 0;
}