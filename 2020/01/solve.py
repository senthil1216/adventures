"""Report Repair."""

# %%
from itertools import combinations
from math import prod
from pathlib import Path


def expense_product(text: str, count: int) -> int:
    for entries in combinations(list(map(int, text.split())), count):
        if sum(entries) == 2020:
            return prod(entries)
    raise ValueError("no matching expenses")


def part1(text: str) -> int:
    return expense_product(text, 2)


def part2(text: str) -> int:
    return expense_product(text, 3)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 365619, f"Expected 365619, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 236873508, f"Expected 236873508, got {answer2}"
