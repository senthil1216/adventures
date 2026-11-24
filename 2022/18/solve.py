"""Boiling Boulders."""

# %%
from collections.abc import Iterator
from pathlib import Path


Point = tuple[int, int, int]


def cubes(text: str) -> set[Point]:
    droplet = set()
    for line in text.splitlines():
        x, y, z = map(int, line.split(","))
        droplet.add((x, y, z))
    return droplet


def adjacent(point: Point) -> Iterator[Point]:
    x, y, z = point
    yield x - 1, y, z
    yield x + 1, y, z
    yield x, y - 1, z
    yield x, y + 1, z
    yield x, y, z - 1
    yield x, y, z + 1


def part1(text: str) -> int:
    droplet = cubes(text)
    return sum(near not in droplet for cube in droplet for near in adjacent(cube))


def part2(text: str) -> int:
    droplet = cubes(text)
    if not droplet:
        return 0

    xs, ys, zs = zip(*droplet)
    min_x, max_x = min(xs) - 1, max(xs) + 1
    min_y, max_y = min(ys) - 1, max(ys) + 1
    min_z, max_z = min(zs) - 1, max(zs) + 1
    outside = (min_x, min_y, min_z)
    pending = [outside]
    visited = {outside}
    surface = 0
    while pending:
        for near in adjacent(pending.pop()):
            if near in droplet:
                surface += 1
                continue
            x, y, z = near
            if (
                min_x <= x <= max_x
                and min_y <= y <= max_y
                and min_z <= z <= max_z
                and near not in visited
            ):
                visited.add(near)
                pending.append(near)
    return surface


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 4340, f"Expected 4340, got {answer1}"

    answer2 = part2(text)
    print(answer2)
