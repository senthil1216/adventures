"""Monkey in the Middle."""

# %%
from collections import deque
from dataclasses import dataclass
from math import lcm
from pathlib import Path


@dataclass
class Monkey:
    items: deque[int]
    operator: str
    operand: str
    divisor: int
    if_true: int
    if_false: int
    inspected: int = 0


def parse(text: str) -> list[Monkey]:
    monkeys = []
    for block in text.strip().split("\n\n"):
        _, items, operation, test, if_true, if_false = block.splitlines()
        _, operator, operand = operation.split(" = ")[1].split()
        item_list = items.split(":", 1)[1].strip()
        monkeys.append(Monkey(
            deque(map(int, item_list.split(","))) if item_list else deque(),
            operator,
            operand,
            int(test.split()[-1]),
            int(if_true.split()[-1]),
            int(if_false.split()[-1]),
        ))
    return monkeys


def monkey_business(text: str, rounds: int, *, relief: bool) -> int:
    monkeys = parse(text)
    modulus = lcm(*(monkey.divisor for monkey in monkeys))
    for _ in range(rounds):
        for monkey in monkeys:
            while monkey.items:
                item = monkey.items.popleft()
                monkey.inspected += 1
                operand = item if monkey.operand == "old" else int(monkey.operand)
                item = item * operand if monkey.operator == "*" else item + operand
                # Without relief, reduce modulo a multiple of every test divisor.
                item = item // 3 if relief else item % modulus
                target = monkey.if_true if item % monkey.divisor == 0 else monkey.if_false
                monkeys[target].items.append(item)
    first, second = sorted(monkey.inspected for monkey in monkeys)[-2:]
    return first * second


def part1(text: str) -> int:
    return monkey_business(text, 20, relief=True)


def part2(text: str) -> int:
    return monkey_business(text, 10_000, relief=False)


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 62491, f"Expected 62491, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 17408399184, f"Expected 17408399184, got {answer2}"
