t = int(input())
p = [1,1,1,2,2]

for _ in range(t):
    n = int(input())
    if n >= len(p):
        for i in range(len(p),n):
            p.append(p[i-1] + p[i-5])
    print(p[n-1])