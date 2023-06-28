import math
def solution(picks, minerals):
    f_cnt = 0
    for x in picks:
        f_cnt += x
    answer = 0
    # 피로도 s,i,d 순서
    fatigue = [[0,0,0] for _ in range(math.ceil(len(minerals)/5))]
    if f_cnt * 5 > len(minerals):
        k = len(minerals)
    else:
        k = f_cnt * 5
    for i in range(k):
        if minerals[i] == 'diamond':
            fatigue[i // 5][2] += 1
            fatigue[i // 5][1] += 5
            fatigue[i // 5][0] += 25
        elif minerals[i] == 'iron':
            fatigue[i // 5][2] += 1
            fatigue[i // 5][1] += 1
            fatigue[i // 5][0] += 5
        elif minerals[i] == 'stone':
            fatigue[i // 5][2] += 1
            fatigue[i // 5][1] += 1
            fatigue[i // 5][0] += 1
    fatigue.sort(key = lambda x : (-x[0],-x[1],-x[2]))

    # 피로도 계산
    for f in fatigue:
        idx = f.index(min(f))
        if idx == 2:
            if picks[0] > 0:
                answer += f[2]
                picks[0] -= 1
            elif picks[1] > 0:
                answer += f[1]
                picks[1] -= 1
            elif picks[2] > 0:
                answer += f[0]
                picks[2] -= 1
        elif idx == 1:
            if picks[1] > 0:
                answer += f[1]
                picks[1] -= 1
            elif picks[0] > 0:
                answer += f[2]
                picks[0] -= 1
            elif picks[2] > 0:
                answer += f[0]
                picks[2] -= 1
        elif idx == 0:
            if picks[2] > 0:
                answer += f[0]
                picks[2] -= 1
            elif picks[0] > 0:
                answer += f[2]
                picks[0] -= 1
            elif picks[1] > 0:
                answer += f[1]
                picks[1] -= 1
    return answer
print(solution([1, 3, 2],["diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"]))