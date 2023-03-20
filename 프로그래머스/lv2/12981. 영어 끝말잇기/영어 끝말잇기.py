def solution(n, words):
    stack = []
    answer = [0,0]
    stack.append(words[0])
    for i in range(1,len(words)):
        # print(words[i][-1])
        if words[i][0] != words[i-1][-1] or words[i] in stack:
            return [i%n + 1, i // n +1]
        stack.append(words[i])
    return answer
            