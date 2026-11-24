"""Supply Stacks."""

# %%
import re
from pathlib import Path


def top_crates(text: str, *, preserve_order: bool) -> str:
    drawing, instructions = text.rstrip().split("\n\n")
    *rows, labels = drawing.splitlines()
    columns = [(int(match[0]), match.start()) for match in re.finditer(r"\d+", labels)]
    stacks = {label: [] for label, _ in columns}
    for row in reversed(rows):
        for label, column in columns:
            if column < len(row) and row[column].isalpha():
                stacks[label].append(row[column])

    for line in instructions.splitlines():
        _, count, _, source, _, destination = line.split()
        size, start, end = int(count), int(source), int(destination)
        if size == 0 or start == end:
            continue
        crates = stacks[start][-size:]
        del stacks[start][-size:]
        stacks[end].extend(crates if preserve_order else reversed(crates))
    return "".join(stack[-1] for stack in stacks.values())


def part1(text: str) -> str:
    return top_crates(text, preserve_order=False)


def part2(text: str) -> str:
    return top_crates(text, preserve_order=True)


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 'SPFMVDTZT', f"Expected SPFMVDTZT, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 'ZFSJBPRFP', f"Expected ZFSJBPRFP, got {answer2}"
