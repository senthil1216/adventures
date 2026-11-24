"""The Treachery of Whales."""

# %%
from pathlib import Path
from statistics import median_low


def part1(text: str) -> int:
    positions = list(map(int, text.split(",")))
    target = median_low(positions)
    return sum(abs(position - target) for position in positions)


def increasing_cost(positions: list[int], target: int) -> int:
    distances = (abs(position - target) for position in positions)
    return sum(distance * (distance + 1) // 2 for distance in distances)


def part2(text: str) -> int:
    positions = list(map(int, text.split(",")))
    # The minimum lies at one of the two integers surrounding the mean.
    lower = sum(positions) // len(positions)
    return min(increasing_cost(positions, lower), increasing_cost(positions, lower + 1))


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 355989, f"Expected 355989, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 102245489, f"Expected 102245489, got {answer2}"
