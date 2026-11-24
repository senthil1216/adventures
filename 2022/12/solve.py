"""Hill Climbing Algorithm."""

# %%
from collections import deque
from pathlib import Path


Point = tuple[int, int]


def distances_from_end(text: str) -> tuple[dict[Point, int], dict[Point, int], Point]:
    heights = {}
    for row, line in enumerate(text.splitlines()):
        for col, char in enumerate(line):
            point = (row, col)
            if char == "S":
                start = point
                char = "a"
            elif char == "E":
                end = point
                char = "z"
            heights[point] = ord(char)

    distances = {end: 0}
    pending = deque([end])
    while pending:
        row, col = point = pending.popleft()
        for near in ((row - 1, col), (row + 1, col), (row, col - 1), (row, col + 1)):
            if near in heights and near not in distances and heights[near] >= heights[point] - 1:
                distances[near] = distances[point] + 1
                pending.append(near)
    return distances, heights, start


def part1(text: str) -> int:
    distances, _, start = distances_from_end(text)
    return distances[start]


def part2(text: str) -> int:
    distances, heights, _ = distances_from_end(text)
    return min(distance for point, distance in distances.items() if heights[point] == ord("a"))


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 456, f"Expected 456, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 454, f"Expected 454, got {answer2}"
