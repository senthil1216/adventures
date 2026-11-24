"""Dive!"""

# %%
from collections.abc import Iterator
from pathlib import Path


def commands(text: str) -> Iterator[tuple[str, int]]:
    for line in text.splitlines():
        direction, distance = line.split()
        yield direction, int(distance)


def part1(text: str) -> int:
    horizontal = depth = 0
    for direction, distance in commands(text):
        if direction == "forward":
            horizontal += distance
        elif direction == "down":
            depth += distance
        elif direction == "up":
            depth -= distance
    return horizontal * depth


def part2(text: str) -> int:
    horizontal = depth = aim = 0
    for direction, distance in commands(text):
        if direction == "forward":
            horizontal += distance
            depth += aim * distance
        elif direction == "down":
            aim += distance
        elif direction == "up":
            aim -= distance
    return horizontal * depth


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 1893605, f"Expected 1893605, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 2120734350, f"Expected 2120734350, got {answer2}"
