"""Mull It Over."""

# %%
import re
from pathlib import Path

MULTIPLY = r"mul\(([0-9]{1,3}),([0-9]{1,3})\)"
INSTRUCTIONS = re.compile(rf"{MULTIPLY}|do\(\)|don't\(\)")


def part1(text: str) -> int:
    return sum(int(left) * int(right) for left, right in re.findall(MULTIPLY, text))


def part2(text: str) -> int:
    enabled = True
    total = 0
    for match in INSTRUCTIONS.finditer(text):
        instruction = match[0]
        if instruction == "do()":
            enabled = True
        elif instruction == "don't()":
            enabled = False
        elif enabled:
            total += int(match[1]) * int(match[2])
    return total


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 196826776, f"Expected 196826776, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 106780429, f"Expected 106780429, got {answer2}"
