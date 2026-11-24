"""Secret Entrance."""

# %%
from collections.abc import Iterator
from pathlib import Path

DIAL_SIZE = 100
START = 50


def rotations(text: str) -> Iterator[int]:
    for line in text.splitlines():
        distance = int(line[1:])
        yield -distance if line[0] == "L" else distance


def part1(text: str) -> int:
    position = START
    count = 0
    for rotation in rotations(text):
        position = (position + rotation) % DIAL_SIZE
        if position == 0:
            count += 1
    return count


def part2(text: str) -> int:
    position = START
    count = 0
    for rotation in rotations(text):
        full_turns, remaining = divmod(abs(rotation), DIAL_SIZE)
        distance_to_zero = -position if rotation > 0 else position
        # Starting at zero requires a full turn to click back onto it.
        clicks_to_zero = distance_to_zero % DIAL_SIZE or DIAL_SIZE
        count += full_turns + (remaining >= clicks_to_zero)
        position = (position + rotation) % DIAL_SIZE
    return count


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 1026, f"Expected 1026, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 5923, f"Expected 5923, got {answer2}"
