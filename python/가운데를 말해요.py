import sys
import heapq
n = int(sys.stdin.readline().rstrip())
# 최소힙 = 
left = []
# 최대힙
right = []
young = []
for _ in range(n):
    speak = int(sys.stdin.readline().rstrip())
    if len(left) == len(right):
        heapq.heappush(left,-speak)
    else:
        heapq.heappush(right,speak)
    
    if right and left and left[0]* -1 > right[0]:
        min = heapq.heappop(right)
        max = heapq.heappop(left) * -1
        heapq.heappush(left,min * -1)
        heapq.heappush(right,max)
    young.append(left[0])
    
for i in young:
    print(i * -1)