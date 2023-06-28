import sys
N = int(sys.stdin.readline())

answer = 0
cnt = 0 
while N > 0:
    if N % 5 == 0:
        answer += N // 5
        break
    elif N % 5 == 3:
        answer += N // 5 + 1
        break
    else:
        answer += 1
        N -= 3

if N < 0:
    print(-1)
else:
    print(answer)