"""Smoke Basin."""

# %%
from collections.abc import Iterator
from math import prod
from pathlib import Path


Point = tuple[int, int]


def heightmap(text: str) -> dict[Point, int]:
    return {
        (row, col): int(height)
        for row, line in enumerate(text.splitlines())
        for col, height in enumerate(line)
    }


def neighbors(point: Point) -> Iterator[Point]:
    row, col = point
    yield row - 1, col
    yield row + 1, col
    yield row, col - 1
    yield row, col + 1


def part1(text: str) -> int:
    heights = heightmap(text)
    return sum(
        height + 1
        for point, height in heights.items()
        if all(height < heights[near] for near in neighbors(point) if near in heights)
    )


def part2(text: str) -> int:
    remaining = {point for point, height in heightmap(text).items() if height != 9}
    sizes = []
    while remaining:
        pending = [remaining.pop()]
        size = 0
        while pending:
            size += 1
            for near in neighbors(pending.pop()):
                if near in remaining:
                    remaining.remove(near)
                    pending.append(near)
        sizes.append(size)
    return prod(sorted(sizes)[-3:])


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 550, f"Expected 550, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 1100682, f"Expected 1100682, got {answer2}"
