"""Distress Signal."""

# %%
import json
from functools import cmp_to_key
from pathlib import Path


Packet = int | list["Packet"]


def compare(left: Packet, right: Packet) -> int:
    if isinstance(left, int) and isinstance(right, int):
        return (left > right) - (left < right)
    if isinstance(left, int):
        left = [left]
    if isinstance(right, int):
        right = [right]
    for first, second in zip(left, right):
        order = compare(first, second)
        if order:
            return order
    return (len(left) > len(right)) - (len(left) < len(right))


def part1(text: str) -> int:
    total = 0
    for index, pair in enumerate(text.strip().split("\n\n"), start=1):
        left, right = map(json.loads, pair.splitlines())
        if compare(left, right) < 0:
            total += index
    return total


def part2(text: str) -> int:
    packets = [json.loads(line) for line in text.splitlines() if line.strip()]
    first, second = [[2]], [[6]]
    packets.extend((first, second))
    packets.sort(key=cmp_to_key(compare))
    positions = (
        index for index, packet in enumerate(packets, start=1)
        if packet is first or packet is second
    )
    position1, position2 = positions
    return position1 * position2


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 5185, f"Expected 5185, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 23751, f"Expected 23751, got {answer2}"
