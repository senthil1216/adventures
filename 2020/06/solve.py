"""Custom Customs."""

# %%
from itertools import groupby
from pathlib import Path


def groups(text: str) -> list[list[set[str]]]:
    return [
        [set(line) for line in lines]
        for blank, lines in groupby(text.splitlines(), key=lambda line: not line.strip())
        if not blank
    ]


def part1(text: str) -> int:
    return sum(len(set.union(*group)) for group in groups(text))


def part2(text: str) -> int:
    return sum(len(set.intersection(*group)) for group in groups(text))


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 6565, f"Expected 6565, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 3137, f"Expected 3137, got {answer2}"
