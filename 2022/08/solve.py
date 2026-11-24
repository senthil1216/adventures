"""Treetop Tree House."""

# %%
from math import prod
from pathlib import Path


def sightlines(grid: list[str], row: int, col: int) -> tuple[str, str, str, str]:
    column = "".join(line[col] for line in grid)
    return (
        grid[row][:col][::-1],
        grid[row][col + 1 :],
        column[:row][::-1],
        column[row + 1 :],
    )


def part1(text: str) -> int:
    grid = text.splitlines()
    return sum(
        any(all(tree < height for tree in view) for view in sightlines(grid, row, col))
        for row, line in enumerate(grid)
        for col, height in enumerate(line)
    )


def viewing_distance(view: str, height: str) -> int:
    for distance, tree in enumerate(view, start=1):
        if tree >= height:
            return distance
    return len(view)


def part2(text: str) -> int:
    grid = text.splitlines()
    return max(
        prod(viewing_distance(view, height) for view in sightlines(grid, row, col))
        for row, line in enumerate(grid)
        for col, height in enumerate(line)
    )


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 1805, f"Expected 1805, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 444528, f"Expected 444528, got {answer2}"
