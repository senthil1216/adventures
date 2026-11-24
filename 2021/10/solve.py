"""Syntax Scoring."""

# %%
from pathlib import Path
from statistics import median_low


CLOSING = {"(": ")", "[": "]", "{": "}", "<": ">"}
ERROR_POINTS = {")": 3, "]": 57, "}": 1197, ">": 25137}
COMPLETION_POINTS = {")": 1, "]": 2, "}": 3, ">": 4}


def check_line(line: str) -> tuple[str | None, list[str]]:
    pending = []
    for character in line:
        if character in CLOSING:
            pending.append(CLOSING[character])
        elif not pending or pending.pop() != character:
            return character, []
    return None, pending


def part1(text: str) -> int:
    return sum(ERROR_POINTS.get(illegal, 0) for illegal, _ in map(check_line, text.splitlines()))


def part2(text: str) -> int:
    scores = []
    for illegal, pending in map(check_line, text.splitlines()):
        if illegal is not None or not pending:
            continue
        score = 0
        for character in reversed(pending):
            score = score * 5 + COMPLETION_POINTS[character]
        scores.append(score)
    return median_low(scores)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 387363, f"Expected 387363, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 4330777059, f"Expected 4330777059, got {answer2}"
