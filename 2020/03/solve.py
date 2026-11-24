"""Toboggan Trajectory."""

# %%
from math import prod
from pathlib import Path


def count_trees(grid: list[str], down: int, right: int) -> int:
    return sum(
        grid[row][step * right % len(grid[row])] == "#"
        for step, row in enumerate(range(down, len(grid), down), start=1)
    )


def part1(text: str) -> int:
    return count_trees(text.splitlines(), 1, 3)


def part2(text: str) -> int:
    grid = text.splitlines()
    slopes = ((1, 1), (1, 3), (1, 5), (1, 7), (2, 1))
    return prod(count_trees(grid, down, right) for down, right in slopes)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 171, f"Expected 171, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 1206576000, f"Expected 1206576000, got {answer2}"
