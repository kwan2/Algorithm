from os import cpu_count
import sys
from collections import deque
input = sys.stdin.readline().rstrip()


def bfs():
    queue = deque([S])
    visited[S] = 1
    while queue:
        current = queue.popleft()
        # 바로 갊 
        if current == G:
            print(visited[G] - 1)
            return 
        up = current + U
        down = current - D
        if 0 <up <= F and not visited[up]:
            queue.append(up)
            visited[up] = visited[current] +1
        if 0 <down <= F and not visited[down]:
            queue.append(down)
            visited[down] = visited[current] +1
        
    else:
        print("use the stairs")

# F층 건물 , G층이 회사 층 , S 현재 위치 , U = 업해서 가는 층 , D = 다운해서 가는 층
F,S,G,U,D = map(int,input.split())
visited = [0 for i in range(F+1)]
bfs()