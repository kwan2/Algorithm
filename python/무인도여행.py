from collections import deque
def solution(maps):
    answer = []
    visited = [
        [0 for _ in range(len(maps[0]))]
        for _ in range(len(maps))
    ]
    # 동서남북
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]

    def bfs(pos):
        x, y = pos
        if visited[x][y] == 1:
            return
        visited[x][y] = 1
        q = deque()
        q.append((x,y))
        k = 0
        while q:
            x, y = q.popleft()
            k += int(maps[x][y])
            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]
                if 0 <= nx < len(maps) and 0 <= ny < len(maps[0]) and visited[nx][ny] == 0 and maps[nx][ny] != 'X':
                    q.append([nx, ny])
                    visited[nx][ny] = 1
        return k
    for i in range(len(maps)):
        for j in range(len(maps[0])):
            if maps[i][j] != 'X':
                num = bfs((i, j))
                if num != None:
                    answer.append(num)
    if not answer:
        return [-1]
    else:
        answer.sort()
        return answer


print(solution(["X591X","X1X5X","X231X", "1XXX1"]))