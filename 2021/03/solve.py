"""Binary Diagnostic."""

# %%
from pathlib import Path


def part1(text: str) -> int:
    rows = text.split()
    gamma = 0
    for column in zip(*rows):
        gamma = (gamma << 1) | (column.count("1") >= column.count("0"))
    mask = (1 << len(rows[0])) - 1
    return gamma * (gamma ^ mask)


def rating(rows: list[str], *, most_common: bool) -> int:
    for index in range(len(rows[0])):
        if len(rows) == 1:
            break
        ones = sum(row[index] == "1" for row in rows)
        zeros = len(rows) - ones
        if zeros == 0 or ones == 0:
            continue
        keep = "1" if (ones >= zeros) == most_common else "0"
        rows = [row for row in rows if row[index] == keep]
    return int(rows[0], 2)


def part2(text: str) -> int:
    rows = text.split()
    return rating(rows, most_common=True) * rating(rows, most_common=False)


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 841526, f"Expected 841526, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 4790390, f"Expected 4790390, got {answer2}"
