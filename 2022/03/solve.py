"""Rucksack Reorganization."""

# %%
from pathlib import Path
from string import ascii_letters


PRIORITIES = {item: priority for priority, item in enumerate(ascii_letters, start=1)}


def common_priority(*rucksacks: str) -> int:
    item, = set.intersection(*(set(rucksack) for rucksack in rucksacks))
    return PRIORITIES[item]


def part1(text: str) -> int:
    total = 0
    for rucksack in text.splitlines():
        middle = len(rucksack) // 2
        total += common_priority(rucksack[:middle], rucksack[middle:])
    return total


def part2(text: str) -> int:
    rucksacks = text.splitlines()
    return sum(
        common_priority(*rucksacks[index : index + 3])
        for index in range(0, len(rucksacks), 3)
    )


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 7553, f"Expected 7553, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 2758, f"Expected 2758, got {answer2}"
