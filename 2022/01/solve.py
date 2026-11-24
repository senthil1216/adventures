"""Calorie Counting."""

# %%
from pathlib import Path


def calorie_totals(text: str) -> list[int]:
    return [sum(map(int, group.split())) for group in text.strip().split("\n\n")]


def part1(text: str) -> int:
    return max(calorie_totals(text))


def part2(text: str) -> int:
    return sum(sorted(calorie_totals(text))[-3:])


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 69177, f"Expected 69177, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 207456, f"Expected 207456, got {answer2}"
