"""Rope Bridge."""

# %%
from pathlib import Path


DIRECTIONS = {"U": (0, 1), "D": (0, -1), "L": (-1, 0), "R": (1, 0)}


def tail_positions(text: str, length: int) -> int:
    knots = [(0, 0)] * length
    visited = {knots[-1]}
    for line in text.splitlines():
        direction, distance = line.split()
        dx, dy = DIRECTIONS[direction]
        for _ in range(int(distance)):
            head_x, head_y = knots[0]
            knots[0] = head_x + dx, head_y + dy
            for index in range(1, length):
                lead_x, lead_y = knots[index - 1]
                x, y = knots[index]
                gap_x, gap_y = lead_x - x, lead_y - y
                if max(abs(gap_x), abs(gap_y)) <= 1:
                    break
                knots[index] = x + (gap_x > 0) - (gap_x < 0), y + (gap_y > 0) - (gap_y < 0)
            visited.add(knots[-1])
    return len(visited)


def part1(text: str) -> int:
    return tail_positions(text, 2)


def part2(text: str) -> int:
    return tail_positions(text, 10)


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 6256, f"Expected 6256, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 2665, f"Expected 2665, got {answer2}"
