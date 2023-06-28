from collections import deque


def solution(board):
    answer = 0
    row = len(board)
    col = len(board[0])
    distance = [[0 for _ in range(col)] for _ in range(row)]
    start = deque()
    # 동 서 남 북
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]
    for i in range(row):
        for j in range(col):
            if board[i][j] == 'R':
                start.append((i, j))
                distance[i][j] = 1
    while start:
        x, y = start.popleft()
        if board[x][y] == 'G':
            return distance[x][y] - 1
        for i in range(4):
            nx, ny = x, y
            while True:
                nx += dx[i]
                ny += dy[i]
                if 0 <= nx < row and 0 <= ny < col and board[nx][ny] == 'D':
                    nx, ny = nx - dx[i], ny - dy[i]
                    break
                if 0 > nx or nx >= row or 0 > ny or ny >= col:
                    nx, ny = nx - dx[i], ny - dy[i]
                    break
            if not distance[nx][ny]:
                distance[nx][ny] = distance[x][y] + 1
                start.append((nx, ny))
    return -1
