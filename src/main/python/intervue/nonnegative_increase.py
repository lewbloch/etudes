# Given an array of non-negative integers find
# the maximum non-negative increase between an element and a later element,
# -1 if any element in the array is negative.
# 0 if no such non-negative difference exists,
#
# [16, 7, 6, 1, 1, 10, 1, 7, 9] => 9
# [7, 6, 1, 16, 1, 10, 1, 7, 9] => 15
# [] => 0
# [12] => 0
# [1, 1, 1, 6, 7, 7, 9, 10, 16] => 15
# [16, 10, 9, 7, 7, 6, 1, 1, 1] => 0
# [16, 7, 6, 1, -3, 10, 1, 7, 9] => -1
# [-3, 10, 1, 7, 9] => -1
#
# Copyright © 2026 Lewis S. Bloch.

def nonnegative_increase(values):
    if not values:
        return 0
    increase = 0
    minim = values[0]

    for pos in range(0, len(values)):
        if values[pos] < 0:
            return -1
        minim = min(values[pos], minim)
        increase = max(increase, values[pos] - minim)

    return increase

examples = (
    [16, 7, 6, 1, 1, 10, 1, 7, 9],
    [7, 6, 1, 16, 1, 10, 1, 7, 9],
    [],
    [12],
    [1, 1, 1, 6, 7, 7, 9, 10, 16],
    [16, 10, 9, 7, 7, 6, 1, 1, 1],
    [16, 7, 6, 1, -3, 10, 1, 7, 9],
    [-3, 10, 1, 7, 9],
)

for vals in examples:
    print(vals, "\n", nonnegative_increase(vals))
