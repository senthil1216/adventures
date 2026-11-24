"""Red-Nosed Reports."""

# %%
from itertools import pairwise
from pathlib import Path


def is_safe(report: list[int]) -> bool:
    differences = [right - left for left, right in pairwise(report)]
    return all(1 <= diff <= 3 for diff in differences) or all(
        -3 <= diff <= -1 for diff in differences
    )


def part1(text: str) -> int:
    return sum(is_safe(list(map(int, line.split()))) for line in text.splitlines())


def safe_with_dampener(report: list[int]) -> bool:
    return is_safe(report) or any(
        is_safe(report[:index] + report[index + 1 :])
        for index in range(len(report))
    )


def part2(text: str) -> int:
    return sum(safe_with_dampener(list(map(int, line.split()))) for line in text.splitlines())


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 213, f"Expected 213, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 285, f"Expected 285, got {answer2}"
