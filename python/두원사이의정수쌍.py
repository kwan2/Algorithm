import math


def solution(r1, r2):
    answer = 0
    for i in range(-r2, r2 + 1):
        m = math.floor(math.sqrt(math.pow(r2, 2) - math.pow(i, 2)))
        if m == 0:
            answer += 1
        elif abs(i) >= r1:
            n = 0
            answer += 2 * (m - n + 1) - 1
        elif abs(i) < r1:
            n = math.ceil(math.sqrt(math.pow(r1, 2) - math.pow(i, 2)))
            answer = answer + 2 * (m - n + 1)

    return answer