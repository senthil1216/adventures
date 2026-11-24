"""Seating System."""

# %%
from itertools import product
from pathlib import Path

DIRECTIONS = set(product((-1, 0, 1), repeat=2)) - {(0, 0)}


def part1(text: str) -> int:
    grid = text.splitlines()
    seats = {
        (row, col)
        for row, line in enumerate(grid)
        for col, cell in enumerate(line)
        if cell in "L#"
    }
    occupied = {seat for seat in seats if grid[seat[0]][seat[1]] == "#"}
    adjacent = {
        (row, col): seats.intersection((row + dr, col + dc) for dr, dc in DIRECTIONS)
        for row, col in seats
    }

    while True:
        next_occupied = set()
        for seat, nearby in adjacent.items():
            count = len(occupied & nearby)
            if (seat in occupied and count < 4) or (seat not in occupied and count == 0):
                next_occupied.add(seat)
        if next_occupied == occupied:
            return len(occupied)
        occupied = next_occupied


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 2243, f"Expected 2243, got {answer1}"
