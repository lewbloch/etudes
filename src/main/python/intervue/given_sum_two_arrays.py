# Find pairs between two arrays that add to a given target value.
# Copyright © 2026 Lewis S. Bloch.

class Kount(dict[int, int]):
    def __missing__(self, key):
        return 0

def pairs_that_sum(lefts, rights, target):
    complems: Kount = Kount()

    for left in lefts:
        complems[left] += 1

    for right in rights:
        left = target - right
        for x in range(0, complems[left]):
            print(left, '\t', right)

first = [17, -7, 0, 9, 3, 9]
second = [16, -7, -6, 1, -1, 10, 1, -7, 9]
targ = 10

pairs_that_sum(first, second, targ)
