"""Cathode-Ray Tube."""

# %%
from collections.abc import Iterator
from pathlib import Path


# Glyphs used by the supplied CRT program.
GLYPHS = {
    ".##./#..#/#..#/####/#..#/#..#": "A",
    "###./#..#/###./#..#/#..#/###.": "B",
    "####/#.../###./#.../#.../####": "E",
    "####/#.../###./#.../#.../#...": "F",
    "#..#/#.#./##../#.#./#.#./#..#": "K",
    "###./#..#/#..#/###./#.#./#..#": "R",
    "####/...#/..#./.#../#.../####": "Z",
}


def register_values(text: str) -> Iterator[int]:
    register = 1
    for line in text.splitlines():
        yield register
        if line.startswith("addx "):
            yield register
            register += int(line[5:])


def part1(text: str) -> int:
    return sum(
        cycle * value
        for cycle, value in enumerate(register_values(text), start=1)
        if cycle in (20, 60, 100, 140, 180, 220)
    )


def render_screen(text: str) -> str:
    pixels = [
        "#" if abs(index % 40 - register) <= 1 else "."
        for index, register in enumerate(register_values(text))
    ]
    return "\n".join("".join(pixels[start : start + 40]) for start in range(0, len(pixels), 40))


def part2(text: str) -> str:
    screen = render_screen(text)
    rows = screen.splitlines()
    if len(rows) != 6 or any(len(row) != 40 for row in rows):
        return screen
    letters = []
    for column in range(0, 40, 5):
        glyph = "/".join(row[column : column + 4] for row in rows)
        if glyph not in GLYPHS or any(row[column + 4] != "." for row in rows):
            return screen
        letters.append(GLYPHS[glyph])
    return "".join(letters)


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 15220, f"Expected 15220, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 'RFZEKBFA', f"Expected RFZEKBFA, got {answer2}"
