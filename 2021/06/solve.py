"""Lanternfish."""

# %%
from pathlib import Path


def population(text: str, days: int) -> int:
    timers = [0] * 9
    for timer in map(int, text.split(",")):
        timers[timer] += 1
    for _ in range(days):
        spawning = timers[0]
        timers = timers[1:] + [spawning]
        timers[6] += spawning
    return sum(timers)


def part1(text: str) -> int:
    return population(text, 80)


def part2(text: str) -> int:
    return population(text, 256)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 358214, f"Expected 358214, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 1622533344325, f"Expected 1622533344325, got {answer2}"
