"""Encoding Error."""

# %%
from itertools import combinations
from pathlib import Path


def first_invalid(numbers: list[int], preamble: int) -> tuple[int, int]:
    for index in range(preamble, len(numbers)):
        target = numbers[index]
        window = numbers[index - preamble : index]
        if all(a + b != target for a, b in combinations(window, 2)):
            return index, target
    raise ValueError("all numbers follow the encoding rule")


def part1(text: str, preamble: int = 25) -> int:
    return first_invalid(list(map(int, text.split())), preamble)[1]


def part2(text: str, preamble: int = 25) -> int:
    numbers = list(map(int, text.split()))
    invalid_index, target = first_invalid(numbers, preamble)
    prefix_positions = {0: 0}
    total = 0
    for end, value in enumerate(numbers[:invalid_index], start=1):
        total += value
        start = prefix_positions.get(total - target)
        if start is not None and end - start >= 2:
            group = numbers[start:end]
            return min(group) + max(group)
        prefix_positions.setdefault(total, end)
    raise ValueError("no contiguous range sums to the invalid number")


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 675280050, f"Expected 675280050, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 96081673, f"Expected 96081673, got {answer2}"
