"""Camp Cleanup."""

# %%
from collections.abc import Iterator
from pathlib import Path


def assignments(text: str) -> Iterator[tuple[int, int, int, int]]:
    for line in text.splitlines():
        first, second = line.split(",")
        start1, end1 = map(int, first.split("-"))
        start2, end2 = map(int, second.split("-"))
        yield start1, end1, start2, end2


def part1(text: str) -> int:
    return sum(
        a <= c <= d <= b or c <= a <= b <= d
        for a, b, c, d in assignments(text)
    )


def part2(text: str) -> int:
    return sum(max(a, c) <= min(b, d) for a, b, c, d in assignments(text))


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 538, f"Expected 538, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 792, f"Expected 792, got {answer2}"
