"""Passage Pathing."""

# %%
from collections import defaultdict
from functools import cache
from pathlib import Path


def count_paths(text: str, *, can_revisit: bool) -> int:
    links: dict[str, set[str]] = defaultdict(set)
    for line in text.splitlines():
        first, second = line.split("-")
        links[first].add(second)
        links[second].add(first)

    @cache
    def visit(cave: str, seen: frozenset[str], revisit_left: bool) -> int:
        if cave == "end":
            return 1
        total = 0
        for neighbor in links[cave]:
            if neighbor == "start":
                continue
            if neighbor.isupper():
                total += visit(neighbor, seen, revisit_left)
            elif neighbor not in seen:
                total += visit(neighbor, seen | {neighbor}, revisit_left)
            elif revisit_left:
                total += visit(neighbor, seen, False)
        return total

    return visit("start", frozenset(), can_revisit)


def part1(text: str) -> int:
    return count_paths(text, can_revisit=False)


def part2(text: str) -> int:
    return count_paths(text, can_revisit=True)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 3463, f"Expected 3463, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 91533, f"Expected 91533, got {answer2}"
