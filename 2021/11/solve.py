"""Dumbo Octopus."""

# %%
from itertools import product
from pathlib import Path


Point = tuple[int, int]
OFFSETS = tuple(
    (dr, dc) for dr, dc in product((-1, 0, 1), repeat=2) if (dr, dc) != (0, 0)
)


def energy_levels(text: str) -> dict[Point, int]:
    return {
        (row, col): int(energy)
        for row, line in enumerate(text.splitlines())
        for col, energy in enumerate(line)
    }


def step(energies: dict[Point, int]) -> int:
    pending = []
    for point in energies:
        energies[point] += 1
        if energies[point] == 10:
            pending.append(point)

    flashed = []
    while pending:
        row, col = pending.pop()
        flashed.append((row, col))
        for dr, dc in OFFSETS:
            near = (row + dr, col + dc)
            if near in energies:
                energies[near] += 1
                if energies[near] == 10:
                    pending.append(near)

    for point in flashed:
        energies[point] = 0
    return len(flashed)


def part1(text: str) -> int:
    energies = energy_levels(text)
    return sum(step(energies) for _ in range(100))


def part2(text: str) -> int:
    energies = energy_levels(text)
    steps = 1
    while step(energies) != len(energies):
        steps += 1
    return steps


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 1599, f"Expected 1599, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 418, f"Expected 418, got {answer2}"
