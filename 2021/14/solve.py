"""Extended Polymerization."""

# %%
from collections import Counter
from itertools import pairwise
from pathlib import Path


def polymer_score(text: str, steps: int) -> int:
    template, instructions = text.strip().split("\n\n")
    rules = dict(line.split(" -> ") for line in instructions.splitlines())
    pairs = Counter(first + second for first, second in pairwise(template))
    letters = Counter(template)

    for _ in range(steps):
        next_pairs: Counter[str] = Counter()
        for pair, count in pairs.items():
            inserted = rules.get(pair)
            if inserted is None:
                next_pairs[pair] += count
                continue
            letters[inserted] += count
            next_pairs[pair[0] + inserted] += count
            next_pairs[inserted + pair[1]] += count
        pairs = next_pairs

    return max(letters.values()) - min(letters.values())


def part1(text: str) -> int:
    return polymer_score(text, 10)


def part2(text: str) -> int:
    return polymer_score(text, 40)


if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 3408, f"Expected 3408, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 3724343376942, f"Expected 3724343376942, got {answer2}"
