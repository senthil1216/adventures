"""Seating System."""

# %%
from collections.abc import Iterator
from itertools import product
from pathlib import Path

DIRECTIONS = set(product((-1, 0, 1), repeat=2)) - {(0, 0)}


def nearby_seats(
    grid: list[str], row: int, col: int, *, line_of_sight: bool
) -> Iterator[tuple[int, int]]:
    for dr, dc in DIRECTIONS:
        r, c = row + dr, col + dc
        while 0 <= r < len(grid) and 0 <= c < len(grid[r]):
            if grid[r][c] in "L#":
                yield r, c
                break
            if not line_of_sight:
                break
            r += dr
            c += dc


def occupied_at_equilibrium(text: str, *, line_of_sight: bool, threshold: int) -> int:
    grid = text.splitlines()
    seats = {
        (row, col)
        for row, line in enumerate(grid)
        for col, cell in enumerate(line)
        if cell in "L#"
    }
    occupied = {seat for seat in seats if grid[seat[0]][seat[1]] == "#"}
    neighbors = {
        (row, col): set(nearby_seats(grid, row, col, line_of_sight=line_of_sight))
        for row, col in seats
    }

    while True:
        next_occupied = set()
        for seat, nearby in neighbors.items():
            count = len(occupied & nearby)
            if (seat in occupied and count < threshold) or (seat not in occupied and count == 0):
                next_occupied.add(seat)
        if next_occupied == occupied:
            return len(occupied)
        occupied = next_occupied


def part1(text: str) -> int:
    return occupied_at_equilibrium(text, line_of_sight=False, threshold=4)


def part2(text: str) -> int:
    return occupied_at_equilibrium(text, line_of_sight=True, threshold=5)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 2243, f"Expected 2243, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 2027, f"Expected 2027, got {answer2}"
