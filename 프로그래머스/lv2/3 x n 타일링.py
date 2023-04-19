def solution(n):
    if n % 2 != 0:
        return 0
    # n = 짝 수 일 때
    f = [5001 for _ in range(n + 1)]
    f[2] = 3
    f[0] = 1
    for i in range(4, n + 1):
        if i % 2 != 0:
            continue
        sum = 1
        for j in range(i-4,0,-2):
            sum += f[j]
        f[i] = (f[i - 2] * 3 + 2 * sum) % 1000000007

    return f[n]