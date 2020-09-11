#include <iostream>
#include <queue>
#include <vector>
#include <string>
using namespace std;

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	// 내림차순 -> less
	priority_queue<int, vector<int>, less<int>> pq;
	
	int N, temp;
	string ans;
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> temp;
		if (temp != 0) pq.push(temp);
		else {
			if (pq.size() != 0) {
				ans += to_string(pq.top()) + "\n";
				pq.pop();
			}
			else {
				ans += "0\n";
			}
		}
	}

	cout << ans << "\n";
	return 0;
}