"""Transparent Origami."""

# %%
from itertools import groupby
from pathlib import Path


Point = tuple[int, int]
Fold = tuple[str, int]

# Glyphs used by the supplied input and the square O in the worked example.
GLYPHS = {
    ".##./#..#/#..#/####/#..#/#..#": "A",
    "####/#.../###./#.../#.../#...": "F",
    ".##./#..#/#.../#.##/#..#/.###": "G",
    "#..#/#..#/####/#..#/#..#/#..#": "H",
    "..##/...#/...#/...#/#..#/.##.": "J",
    "#####/#...#/#...#/#...#/#####": "O",
}


def parse(text: str) -> tuple[set[Point], list[Fold]]:
    coordinates, instructions = text.strip().split("\n\n")
    dots = set()
    for line in coordinates.splitlines():
        x, y = map(int, line.split(","))
        dots.add((x, y))

    folds = []
    for line in instructions.splitlines():
        axis, value = line.removeprefix("fold along ").split("=")
        folds.append((axis, int(value)))
    return dots, folds


def fold(dots: set[Point], instruction: Fold) -> set[Point]:
    axis, line = instruction
    if axis == "x":
        return {(min(x, 2 * line - x), y) for x, y in dots if x != line}
    return {(x, min(y, 2 * line - y)) for x, y in dots if y != line}


def render(dots: set[Point]) -> list[str]:
    min_x, max_x = min(x for x, _ in dots), max(x for x, _ in dots)
    min_y, max_y = min(y for _, y in dots), max(y for _, y in dots)
    return [
        "".join("#" if (x, y) in dots else "." for x in range(min_x, max_x + 1))
        for y in range(min_y, max_y + 1)
    ]


def read_code(rows: list[str]) -> str:
    letters = []
    for blank, columns in groupby(zip(*rows), key=lambda column: "#" not in column):
        if blank:
            continue
        glyph = "/".join("".join(row) for row in zip(*columns))
        if glyph not in GLYPHS:
            raise ValueError("Unrecognized letter in folded paper:\n" + "\n".join(rows))
        letters.append(GLYPHS[glyph])
    return "".join(letters)


def part1(text: str) -> int:
    dots, folds = parse(text)
    return len(fold(dots, folds[0]))


def part2(text: str) -> str:
    dots, folds = parse(text)
    for instruction in folds:
        dots = fold(dots, instruction)
    return read_code(render(dots))


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 785, f"Expected 785, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 'FJAHJGAH', f"Expected FJAHJGAH, got {answer2}"
