#include <iostream>
#include <algorithm>	// sort와 unique 사용
#include <vector>
using namespace std;

int N;
vector<int> v, idx;

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int a;
		cin >> a;
		v.push_back(a);	// push_back: 마지막 원소 뒤에 삽입
		idx.push_back(a);
	}
	
	sort(idx.begin(), idx.end());	// 정렬
	idx.resize(unique(idx.begin(), idx.end()) - idx.begin());
	//idx.erase(unique(idx.begin(), idx.end()), idx.end());	// 중복 제거

	for (int a : v) {
		int pos = lower_bound(idx.begin(), idx.end(), a) - idx.begin();
		cout << pos << ' ';
	}
	return 0;
}