"""Beacon Exclusion Zone."""

# %%
import re
from pathlib import Path


Sensor = tuple[int, int, int]
Point = tuple[int, int]


def scan(text: str) -> tuple[list[Sensor], set[Point]]:
    sensors = []
    beacons = set()
    for line in text.splitlines():
        x, y, bx, by = map(int, re.findall(r"-?\d+", line))
        sensors.append((x, y, abs(x - bx) + abs(y - by)))
        beacons.add((bx, by))
    return sensors, beacons


def part1(text: str, row: int = 2_000_000) -> int:
    sensors, beacons = scan(text)
    intervals = []
    for x, y, radius in sensors:
        reach = radius - abs(y - row)
        if reach >= 0:
            intervals.append((x - reach, x + reach))

    merged: list[tuple[int, int]] = []
    for start, end in sorted(intervals):
        if merged and start <= merged[-1][1] + 1:
            left, right = merged.pop()
            merged.append((left, max(end, right)))
        else:
            merged.append((start, end))
    covered = sum(end - start + 1 for start, end in merged)
    known = sum(y == row and any(start <= x <= end for start, end in merged) for x, y in beacons)
    return covered - known


def part2(text: str, limit: int = 4_000_000) -> int:
    sensors, _ = scan(text)
    sums = set()
    differences = set()
    for x, y, radius in sensors:
        edge = radius + 1
        sums.update((x + y - edge, x + y + edge))
        differences.update((x - y - edge, x - y + edge))

    # An isolated gap touches sensor perimeters or the outside of the search box.
    candidates = {(0, 0), (0, limit), (limit, 0), (limit, limit)}
    for total in sums:
        candidates.update(((0, total), (limit, total - limit), (total, 0), (total - limit, limit)))
        for difference in differences:
            x = (total + difference) // 2
            y = (total - difference) // 2
            candidates.update(((x, y), (x + 1, y), (x, y + 1), (x + 1, y + 1)))
    for difference in differences:
        candidates.update(
            ((0, -difference), (limit, limit - difference), (difference, 0), (difference + limit, limit))
        )

    for x, y in sorted(candidates):
        if 0 <= x <= limit and 0 <= y <= limit and all(
            abs(x - sx) + abs(y - sy) > radius for sx, sy, radius in sensors
        ):
            return x * 4_000_000 + y
    raise ValueError("No uncovered position in the search area")


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 5108096, f"Expected 5108096, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 10553942650264, f"Expected 10553942650264, got {answer2}"
