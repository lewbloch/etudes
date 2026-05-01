# Find the length of the longest substring(s) that contain no character twice.
# Restricted to the 26 letters of the standard English alphabet, lower case.
#
# Copyright © 2026 Lewis S. Bloch.

class Posns(dict[str, int]):
    def __missing__(self, key):
        return -1

def longest_distinct_substring(source: str) -> int:
    if not source:
        return 0

    posns = Posns()
    max_len: int = 0
    start: int = 0

    for curr in range(len(source)):
        ch: str = source[curr]
        prev: int = posns[ch]
        if prev >= start:
            start = prev + 1
        posns[ch] = curr
        max_len = max(max_len, curr - start + 1)

    return max_len

if __name__ == "__main__":
    examples: list[str] = [
        "",             # 0
        "alphabet",     # 7
        "baabaa",       # 2
        "aabaa",        # 2
        "mississippi",  # 3
        "quickdog",     # 8
    ]

    for example in examples:
        print(f'"{example}"\n', longest_distinct_substring(example))
