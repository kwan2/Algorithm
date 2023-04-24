
def solution(weights):
    answer = 0
    dic = dict()
    for w in weights:
        dic.setdefault(w,0)
        dic[w] += 1
    for key, item in dic.items():
        if item >= 2:
            answer += item * (item - 1) // 2
    keys = list(dic.keys())
    for k in keys:
        if (k * 2) in keys:
            answer += dic[k] * dic[k*2]
        if (k * 2 / 3) in keys:
            answer += dic[k] * dic[k*2/3]
        if (k * 3 / 4) in keys:
            answer += dic[k] * dic[k*3/4]
    return answer