"""Binary Boarding."""

# %%
from pathlib import Path


SEAT_BITS = str.maketrans("FBLR", "0101")


def seat_ids(text: str) -> set[int]:
    return {int(code.translate(SEAT_BITS), 2) for code in text.split()}


def part1(text: str) -> int:
    return max(seat_ids(text))


def part2(text: str) -> int:
    seats = seat_ids(text)
    for seat in sorted(seats):
        if seat + 1 not in seats and seat + 2 in seats:
            return seat + 1
    raise ValueError("no missing seat with both neighbors present")


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 832, f"Expected 832, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 517, f"Expected 517, got {answer2}"
