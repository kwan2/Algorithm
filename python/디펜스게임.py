from heapq import heappop, heappush

def solution(n, k, enemy):
    answer = 0
    heap = []
    if len(enemy) == k:
        return k
    for i in range(len(enemy)):
        n -= enemy[i]
        heappush(heap, -enemy[i])
        if n < 0:
            if heap and k > 0:
                k -= 1
                n -= heappop(heap)
            else:
                answer = i
                break
        answer += 1
    return answer

print(solution(7, 3, [4, 2, 4, 5, 3, 3, 1]))