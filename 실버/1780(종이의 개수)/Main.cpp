#include <iostream>
#include <stdlib.h>
using namespace std;

int N;
int a, b, c;
int ** paper;

bool check(int startX, int endX, int startY, int endY) {
	int color = paper[startY][startX];

	for (int i = startY; i < endY; i++) {
		for (int j = startX; j < endX; j++) {
			if (paper[i][j] != color) {
				return false;
			}
		}
	}
	return true;
}

void make(int startX, int endX, int startY, int endY, int len) {
	// 같지 않다면 또 나누어야 함
	if (!check(startX, endX, startY, endY)) {
		make(startX, startX + len/3, startY, startY + len/3, len/3);	// 1사분면
		make(startX + len/3, startX + 2*len/3, startY, startY + len/3, len/3);	// 2
		make(startX + 2*len/3, startX + len, startY, startY + len/3, len / 3);	// 3

		make(startX, startX + len/3, startY + len / 3, startY + 2*len/3, len / 3);	// 4
		make(startX + len/3, startX + 2*len/3, startY + len/3, startY + 2*len/3, len/3); // 5
		make(startX + 2*len/3, startX + len, startY + len/3, startY + 2*len/3, len/3);	// 6
		
		make(startX, startX + len/3, startY + 2*len/3, startY + len, len/3);	// 7
		make(startX + len/3, startX + 2*len/3, startY + 2*len/3, startY + len, len/3);	// 8
		make(startX + 2*len/3, startX + len, startY + 2*len/3, startY + len, len/3);	//9
	}
	else {
		if (paper[startY][startX] == -1)
			a++;
		else if (paper[startY][startX] == 0)
			b++;
		else
			c++;
	}
}

int main() {
	// IO 속도 향상
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N;
	a = b = c = 0;

	paper = (int**)malloc(sizeof(int*) * N);
	for (int i = 0; i < N; i++)
		paper[i] = (int*)malloc(sizeof(int) * N);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> paper[i][j];
		}
	}

	if (check(0, N, 0, N)) {
		if (paper[0][0] == -1) {
			cout << 1 << "\n";
			cout << 0 << "\n";
			cout << 0 << "\n";
		}
		else if (paper[0][0] == 0) {
			cout << 0 << "\n";
			cout << 1 << "\n";
			cout << 0 << "\n";
		}
		else{
			cout << 0 << "\n";
			cout << 0 << "\n";
			cout << 1 << "\n";
		}
	}
	else {
		make(0, N / 3, 0, N / 3, N / 3);	// 1사분면
		make(N/3, 2*N/3, 0, N/3, N/3);	// 2사분면
		make(2*N/3, N, 0, N/3, N/3);	// 3사분면

		make(0, N/3, N/3, 2*N/3, N/3);	// 4사분면
		make(N/3, 2*N/3, N/3, 2*N/3, N/3);
		make(2*N/3, N, N/3, 2*N/3, N/3);

		make(0, N/3, 2*N/3, N, N/3);
		make(N/3, 2*N/3, 2*N/3, N, N/3);
		make(2*N/3, N, 2*N/3, N, N/3);

		cout << a << endl;
		cout << b << endl;
		cout << c << endl;
	}
	return 0;
}