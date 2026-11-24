"""Regolith Reservoir."""

# %%
from itertools import pairwise
from pathlib import Path


Point = tuple[int, int]
SOURCE = (500, 0)


def rocks(text: str) -> set[Point]:
    occupied = set()
    for line in text.splitlines():
        path = [tuple(map(int, point.split(","))) for point in line.split(" -> ")]
        occupied.update(path)
        for (x1, y1), (x2, y2) in pairwise(path):
            for x in range(min(x1, x2), max(x1, x2) + 1):
                for y in range(min(y1, y2), max(y1, y2) + 1):
                    occupied.add((x, y))
    return occupied


def settled_sand(text: str, *, has_floor: bool) -> int:
    occupied = rocks(text)
    abyss = max(y for _, y in occupied)
    floor = abyss + 2
    count = 0
    path = [SOURCE]
    while path and SOURCE not in occupied:
        x, y = path[-1]
        if not has_floor and y > abyss:
            break

        for near in ((x, y + 1), (x - 1, y + 1), (x + 1, y + 1)):
            if near not in occupied and (not has_floor or near[1] < floor):
                path.append(near)
                break
        else:
            occupied.add(path.pop())
            count += 1
    return count


def part1(text: str) -> int:
    return settled_sand(text, has_floor=False)


def part2(text: str) -> int:
    return settled_sand(text, has_floor=True)


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 715, f"Expected 715, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 25248, f"Expected 25248, got {answer2}"
