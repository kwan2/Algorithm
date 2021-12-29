#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {

    for (int i = 0; i < lost.size(); i++) {
        for (int j = 0; j < reserve.size(); j++) {
            if (lost[i] == reserve[j]) {
                lost.erase(lost.begin() + i);
                reserve.erase(reserve.begin() + j);
                i = -1;
                break;
            }
        }
    }
    int answer = n - lost.size();
    sort(lost.begin(), lost.end());
    sort(reserve.begin(), reserve.end());
    for (int i = 0; i < lost.size(); i++) {
        for (int j = 0; j < reserve.size(); j++) {
            if (lost[i] == reserve[j] - 1 || lost[i] == reserve[j] + 1) {
                answer++;
                reserve.erase(reserve.begin() + j);
                break;
            }
        }
    }
    return answer;
}
int main() {
    int n;
    vector<int> lost;
    vector<int> reserve;
    int temp;
    cout << "총 학생수 : ";
    cin >> n;
    cout << "도난당한 학생(-1 입력 시 끝): ";
    while (true) {
        cin >> temp;
        if (temp == -1) {
            break;
        }
        lost.push_back(temp);
    }
    cout << "여별을 가져온 학생(end시 입력 끝): ";
    while (true) {
        cin >> temp;
        if (temp == -1) {
            break;
        }
        reserve.push_back(temp);
    }
    /*for (int i = 0; i < lost.size(); i++)
        cout << lost[i];
    cout << endl;
    for (int i = 0; i < reserve.size(); i++)
        cout << reserve[i];
    cout << endl;
    */
    cout << "최종 return 값 : " << solution(n,lost,reserve)<<endl;
}