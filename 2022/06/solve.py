"""Tuning Trouble."""

# %%
from pathlib import Path


def marker(text: str, length: int) -> int:
    stream = text.strip()
    for end in range(length, len(stream) + 1):
        if len(set(stream[end - length : end])) == length:
            return end
    raise ValueError(f"No marker of length {length} found")


def part1(text: str) -> int:
    return marker(text, 4)


def part2(text: str) -> int:
    return marker(text, 14)


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 1909, f"Expected 1909, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 3380, f"Expected 3380, got {answer2}"
