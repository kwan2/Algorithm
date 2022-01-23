#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> people, int limit) {
    int answer = 0;
    sort(people.begin(),people.end());
    int index = 0;
    while(index < people.size()){
       int max = people.back();
        people.pop_back();
        if(people[index] + max <= limit){
            answer++;
            index++;
        }else{
            answer++;
        }
    }
    
    return answer;
}