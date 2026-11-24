"""Rock Paper Scissors."""

# %%
from collections.abc import Iterator
from pathlib import Path


def rounds(text: str) -> Iterator[tuple[int, int]]:
    for line in text.splitlines():
        opponent, code = line.split()
        yield ord(opponent) - ord("A"), ord(code) - ord("X")


def part1(text: str) -> int:
    score = 0
    for opponent, player in rounds(text):
        outcome = (player - opponent) % 3
        score += player + 1 + (3, 6, 0)[outcome]
    return score


def part2(text: str) -> int:
    score = 0
    for opponent, outcome in rounds(text):
        player = (opponent + outcome - 1) % 3
        score += player + 1 + 3 * outcome
    return score


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 13009, f"Expected 13009, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 10398, f"Expected 10398, got {answer2}"
