"""Trebuchet?!"""

# %%
from pathlib import Path

WORD_DIGITS = {
    "one": 1,
    "two": 2,
    "three": 3,
    "four": 4,
    "five": 5,
    "six": 6,
    "seven": 7,
    "eight": 8,
    "nine": 9,
}


def calibration_value(line: str, *, include_words: bool) -> int:
    digits = []
    for index, char in enumerate(line):
        if "0" <= char <= "9":
            digits.append(int(char))
        elif include_words:
            # Checking every starting position lets words share a letter.
            for word, digit in WORD_DIGITS.items():
                if line.startswith(word, index):
                    digits.append(digit)
                    break

    if not digits:
        raise ValueError(f"No calibration digit in {line!r}")
    return 10 * digits[0] + digits[-1]


def part1(text: str) -> int:
    return sum(calibration_value(line, include_words=False) for line in text.splitlines())


def part2(text: str) -> int:
    return sum(calibration_value(line, include_words=True) for line in text.splitlines())


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 54927, f"Expected 54927, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 54581, f"Expected 54581, got {answer2}"

# %%
