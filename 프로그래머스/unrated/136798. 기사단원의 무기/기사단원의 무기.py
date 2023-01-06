def solution(number, limit, power):
    answer= 0
    weapons = [] 
    for num in range(number):
        temp = []
        for i in range(1, int((num+1)**(1/2)) + 1):
            if (num+1) % i == 0:
                temp.append(i)
                if((i**2) != num+1):
                    temp.append((num+1)//i)
        weapons.append(len(temp))
    for weapon in weapons:
        if weapon <= limit:
            answer += weapon
        else:
            answer += power
    return answer