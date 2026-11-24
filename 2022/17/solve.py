"""Pyroclastic Flow."""

# %%
from pathlib import Path


SHAPES = (
    ((0, 0), (1, 0), (2, 0), (3, 0)),
    ((1, 0), (0, 1), (1, 1), (2, 1), (1, 2)),
    ((0, 0), (1, 0), (2, 0), (2, 1), (2, 2)),
    ((0, 0), (0, 1), (0, 2), (0, 3)),
    ((0, 0), (1, 0), (0, 1), (1, 1)),
)
WIDTH = 7
SNAPSHOT_DEPTH = 64


def tower_height(text: str, target: int) -> int:
    jets = text.strip()
    rows = {0: (1 << WIDTH) - 1}
    height = jet_index = count = skipped_height = 0
    seen: dict[tuple[int, int, tuple[int, ...]], tuple[int, int]] = {}
    lowest_checks = []

    def fits(shape: tuple[tuple[int, int], ...], x: int, y: int) -> bool:
        return all(
            0 <= x + dx < WIDTH and not rows.get(y + dy, 0) & (1 << (x + dx))
            for dx, dy in shape
        )

    while count < target:
        shape = SHAPES[count % len(SHAPES)]
        x, y = 2, height + 4
        while True:
            push = 1 if jets[jet_index] == ">" else -1
            jet_index = (jet_index + 1) % len(jets)
            if fits(shape, x + push, y):
                x += push
            if not fits(shape, x, y - 1):
                break
            y -= 1

        for dx, dy in shape:
            row = y + dy
            rows[row] = rows.get(row, 0) | (1 << (x + dx))
            height = max(height, row)
        count += 1
        if skipped_height:
            continue
        lowest_checks.append(y - 1)
        if height < SNAPSHOT_DEPTH:
            continue

        bottom = height - SNAPSHOT_DEPTH + 1
        snapshot = tuple(rows.get(row, 0) for row in range(bottom, height + 1))
        key = (count % len(SHAPES), jet_index, snapshot)
        previous = seen.get(key)
        seen[key] = (count, height)
        if previous is None:
            continue

        old_count, old_height = previous
        period = count - old_count
        repeats = (target - count) // period
        # A repeat is safe only if the cycle never checked below its saved rows.
        if repeats and min(lowest_checks[old_count:count]) >= old_height - SNAPSHOT_DEPTH + 1:
            skipped_height = repeats * (height - old_height)
            count += repeats * period

    return height + skipped_height


def part1(text: str) -> int:
    return tower_height(text, 2022)


def part2(text: str) -> int:
    return tower_height(text, 1_000_000_000_000)


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 3085, f"Expected 3085, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 1535483870924, f"Expected 1535483870924, got {answer2}"
