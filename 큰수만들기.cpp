#include <string>
#include <vector>

using namespace std;

string solution(string number, int k) {
    string answer = "";
    int index = 0;
    int num = number.length() - k;
    for(int i =0;i < num;i++){
        char max = number[index];
        int maxIdx = index;
        for(int j = index; j<= k+i;j++){
            if(max<number[j]){
                max = number[j];
                maxIdx = j;
            }
        }
        index = maxIdx +1;
        answer+=max;
    }
    return answer;
}