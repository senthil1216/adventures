"""Giant Squid."""

# %%
from collections.abc import Iterator
from pathlib import Path


def winning_scores(text: str) -> Iterator[int]:
    draws, *blocks = text.strip().split("\n\n")
    boards = [[list(map(int, row.split())) for row in block.splitlines()] for block in blocks]
    lines = [[set(row) for row in board] + [set(column) for column in zip(*board)] for board in boards]
    remaining = dict(enumerate(boards))
    drawn: set[int] = set()

    for number in map(int, draws.split(",")):
        drawn.add(number)
        for index, board in list(remaining.items()):
            if any(line <= drawn for line in lines[index]):
                del remaining[index]
                yield number * sum(value for row in board for value in row if value not in drawn)
        if not remaining:
            break


def part1(text: str) -> int:
    return next(winning_scores(text))


def part2(text: str) -> int:
    return list(winning_scores(text))[-1]


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 63552, f"Expected 63552, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 9020, f"Expected 9020, got {answer2}"
