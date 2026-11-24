"""No Space Left On Device."""

# %%
from collections import defaultdict
from pathlib import Path


def directory_sizes(text: str) -> dict[tuple[str, ...], int]:
    current: tuple[str, ...] = ()
    sizes: dict[tuple[str, ...], int] = defaultdict(int)
    seen_files: set[tuple[str, ...]] = set()
    for line in text.splitlines():
        if line.startswith("$ cd "):
            destination = line[5:]
            if destination == "/":
                current = ()
            elif destination == "..":
                current = current[:-1]
            else:
                current += (destination,)
        elif line[0].isdigit():
            size, filename = line.split(maxsplit=1)
            file_path = (*current, filename)
            if file_path not in seen_files:
                seen_files.add(file_path)
                for depth in range(len(current) + 1):
                    sizes[current[:depth]] += int(size)
    return sizes


def part1(text: str) -> int:
    return sum(size for size in directory_sizes(text).values() if size <= 100_000)


def part2(text: str) -> int:
    sizes = directory_sizes(text)
    needed = sizes[()] - 40_000_000
    return min(size for size in sizes.values() if size >= needed)


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 1297683, f"Expected 1297683, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 5756764, f"Expected 5756764, got {answer2}"
