from collections import deque


def solution(maps):
    answer = 0
    q = deque()
    flag = False
    visited = [
        [0 for _ in range(len(maps[0]))]
        for _ in range(len(maps))
    ]
    visited_n = [
        [0 for _ in range(len(maps[0]))]
        for _ in range(len(maps))
    ]
    distance = [
        [0 for _ in range(len(maps[0]))]
        for _ in range(len(maps))
    ]
    # 동서남북
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    for i in range(len(maps)):
        for j in range(len(maps[i])):
            if maps[i][j] == 'S':
                q.append((i, j, False))

    while q:
        x, y, flag = q.popleft()

        if not flag:
            visited_n[x][y] = True
        else:
            visited[x][y] = True

        if maps[x][y] == 'L':
            flag = True
            visited[x][y] = True

        if flag and maps[x][y] == 'E':
            return distance[x][y]

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < len(maps) and 0 <= ny < len(maps[0]) and maps[nx][ny] != 'X':
                if not flag and not visited_n[nx][ny]:
                    visited_n[nx][ny] = True
                    q.append((nx, ny, flag))
                elif flag and not visited[nx][ny]:
                    q.append((nx, ny, flag))
                    visited[nx][ny] = True
                distance[nx][ny] = distance[x][y] + 1
    return -1