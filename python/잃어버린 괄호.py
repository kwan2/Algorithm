import sys

input = str(sys.stdin.readline().rstrip())

n = input.split('-')

answer = 0
for i in n[0].split('+'):
    answer += int(i)
for i in n[1:]:
    for j in i.split('+'):
        answer -= int(j)

print(answer)
