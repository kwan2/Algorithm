def solution(clothes):
    answer = 1
    dic = {}
    for cloth, cloth_type in clothes:
        dic[cloth_type] = dic.get(cloth_type,1) + 1
    for value in dic.values():
         answer *= value
    answer -= 1
    return answer