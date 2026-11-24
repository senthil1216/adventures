"""Hydrothermal Venture."""

# %%
from collections import Counter
from pathlib import Path


def overlaps(text: str, *, include_diagonals: bool) -> int:
    counts: Counter[tuple[int, int]] = Counter()
    for line in text.splitlines():
        start, end = line.split(" -> ")
        x1, y1 = map(int, start.split(","))
        x2, y2 = map(int, end.split(","))
        dx, dy = x2 - x1, y2 - y1
        if dx and dy and (not include_diagonals or abs(dx) != abs(dy)):
            continue
        step_x = (dx > 0) - (dx < 0)
        step_y = (dy > 0) - (dy < 0)
        for step in range(max(abs(dx), abs(dy)) + 1):
            counts[x1 + step * step_x, y1 + step * step_y] += 1
    return sum(count >= 2 for count in counts.values())


def part1(text: str) -> int:
    return overlaps(text, include_diagonals=False)


def part2(text: str) -> int:
    return overlaps(text, include_diagonals=True)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 6283, f"Expected 6283, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 18864, f"Expected 18864, got {answer2}"
