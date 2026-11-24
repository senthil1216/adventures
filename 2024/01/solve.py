"""Historian Hysteria."""

# %%
from collections import Counter
from pathlib import Path


def location_lists(text: str) -> tuple[list[int], list[int]]:
    left, right = [], []
    for line in text.splitlines():
        first, second = map(int, line.split())
        left.append(first)
        right.append(second)
    return left, right


def part1(text: str) -> int:
    left, right = location_lists(text)
    return sum(abs(a - b) for a, b in zip(sorted(left), sorted(right)))


def part2(text: str) -> int:
    left, right = location_lists(text)
    counts = Counter(right)
    return sum(value * counts[value] for value in left)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 2756096, f"Expected 2756096, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 23117829, f"Expected 23117829, got {answer2}"
