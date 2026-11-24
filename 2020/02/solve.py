"""Password Philosophy."""

# %%
from collections.abc import Iterator
from pathlib import Path


def policies(text: str) -> Iterator[tuple[int, int, str, str]]:
    for line in text.splitlines():
        interval, letter, password = line.split()
        first, second = map(int, interval.split("-"))
        yield first, second, letter.removesuffix(":"), password


def part1(text: str) -> int:
    return sum(
        low <= password.count(letter) <= high
        for low, high, letter, password in policies(text)
    )


def part2(text: str) -> int:
    return sum(
        (password[first - 1 : first] == letter) != (password[second - 1 : second] == letter)
        for first, second, letter, password in policies(text)
    )


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 638, f"Expected 638, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 699, f"Expected 699, got {answer2}"
