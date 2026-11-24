"""Sonar Sweep."""

# %%
from pathlib import Path


def count_increases(text: str, window: int) -> int:
    depths = list(map(int, text.split()))
    return sum(new > old for old, new in zip(depths, depths[window:]))


def part1(text: str) -> int:
    return count_increases(text, 1)


def part2(text: str) -> int:
    # Overlapping windows differ only in the reading entering and leaving.
    return count_increases(text, 3)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 1713, f"Expected 1713, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 1734, f"Expected 1734, got {answer2}"
