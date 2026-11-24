"""Handheld Halting."""

# %%
from pathlib import Path


def instructions(text: str) -> list[tuple[str, int]]:
    return [(op, int(value)) for op, value in map(str.split, text.splitlines())]


def execute(program: list[tuple[str, int]]) -> tuple[bool, int]:
    seen = set()
    address = accumulator = 0
    while 0 <= address < len(program) and address not in seen:
        seen.add(address)
        op, value = program[address]
        if op == "acc":
            accumulator += value
        address += value if op == "jmp" else 1
    return address == len(program), accumulator


def part1(text: str) -> int:
    return execute(instructions(text))[1]


def part2(text: str) -> int:
    program = instructions(text)
    swaps = {"jmp": "nop", "nop": "jmp"}
    for index, (op, value) in enumerate(program):
        if op in swaps:
            patched = program.copy()
            patched[index] = (swaps[op], value)
            terminated, accumulator = execute(patched)
            if terminated:
                return accumulator
    raise ValueError("no single instruction change terminates the program")


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 1600, f"Expected 1600, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 1543, f"Expected 1543, got {answer2}"
