"""Adapter Array."""

# %%
from collections import Counter
from itertools import pairwise
from pathlib import Path


def part1(text: str) -> int:
    chain = [0, *sorted(map(int, text.split()))]
    differences = Counter(right - left for left, right in pairwise(chain))
    return differences[1] * (differences[3] + 1)


def part2(text: str) -> int:
    adapters = sorted(map(int, text.split()))
    paths = {0: 1}
    for adapter in adapters:
        paths[adapter] = sum(paths.get(adapter - step, 0) for step in (1, 2, 3))
    return paths[adapters[-1]] if adapters else 1


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 1885, f"Expected 1885, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 2024782584832, f"Expected 2024782584832, got {answer2}"
