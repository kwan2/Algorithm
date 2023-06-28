def solution(cards):
    answer = []
    result = 0
    visited = [0 for _ in range(len(cards) + 1)]
    for i in range(len(cards)):
        if visited[i] == 1:
            continue
        visited[i] = 1
        ni = cards[i] - 1
        temp = [cards[i]]
        while visited[ni] == 0:
            visited[ni] = 1
            temp.append(cards[ni])
            ni = cards[ni] - 1
        answer.append(temp)
    answer.sort(key = lambda x: -len(x))
    if len(answer) == 1:
        return 0
    else:
        return len(answer[0]) * len(answer[1])
    return 