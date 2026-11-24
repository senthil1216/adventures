"""Handy Haversacks."""

# %%
import re
from collections import defaultdict
from functools import cache
from pathlib import Path


TARGET = "shiny gold"


def bag_rules(text: str) -> dict[str, list[tuple[int, str]]]:
    rules = {}
    for line in text.splitlines():
        outer, contents = line.split(" bags contain ")
        rules[outer] = [
            (int(count), color)
            for count, color in re.findall(r"(\d+) ([a-z ]+) bags?", contents)
        ]
    return rules


def part1(text: str) -> int:
    containers = defaultdict(set)
    for outer, contents in bag_rules(text).items():
        for _, inner in contents:
            containers[inner].add(outer)

    seen = {TARGET}
    pending = [TARGET]
    while pending:
        for outer in containers[pending.pop()] - seen:
            seen.add(outer)
            pending.append(outer)
    return len(seen) - 1


def part2(text: str) -> int:
    rules = bag_rules(text)

    @cache
    def count_inside(color: str) -> int:
        return sum(count * (1 + count_inside(inner)) for count, inner in rules[color])

    return count_inside(TARGET)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 233, f"Expected 233, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 421550, f"Expected 421550, got {answer2}"
