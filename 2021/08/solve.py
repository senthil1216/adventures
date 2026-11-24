"""Seven Segment Search."""

# %%
from collections.abc import Iterator
from pathlib import Path


def entries(text: str) -> Iterator[tuple[list[str], list[str]]]:
    for line in text.splitlines():
        signals, output = line.split("|")
        yield signals.split(), output.split()


def part1(text: str) -> int:
    return sum(len(pattern) in {2, 3, 4, 7} for _, output in entries(text) for pattern in output)


def decode(signals: list[str], output: list[str]) -> int:
    by_length: dict[int, list[frozenset[str]]] = {}
    for signal in signals:
        by_length.setdefault(len(signal), []).append(frozenset(signal))

    one, seven, four, eight = (by_length[length][0] for length in (2, 3, 4, 7))
    six_segments = by_length[6]
    nine = next(pattern for pattern in six_segments if four <= pattern)
    zero = next(pattern for pattern in six_segments if pattern != nine and one <= pattern)
    six = next(pattern for pattern in six_segments if pattern not in (zero, nine))

    five_segments = by_length[5]
    three = next(pattern for pattern in five_segments if one <= pattern)
    five = next(pattern for pattern in five_segments if pattern <= six)
    two = next(pattern for pattern in five_segments if pattern not in (three, five))
    digits = {
        pattern: str(digit)
        for digit, pattern in enumerate((zero, one, two, three, four, five, six, seven, eight, nine))
    }
    return int("".join(digits[frozenset(pattern)] for pattern in output))


def part2(text: str) -> int:
    return sum(decode(signals, output) for signals, output in entries(text))


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 445, f"Expected 445, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 1043101, f"Expected 1043101, got {answer2}"
