import sys
T = int(sys.stdin.readline().rstrip())

for _ in range(T):
    k = int(sys.stdin.readline().rstrip())
    n = int(sys.stdin.readline().rstrip())
    answer = [ x for x in range(1,n+1)]
    for _ in range(k):
        for j in range(1,n):
            answer[j] += answer[j-1]
    print(answer[-1])