"""Passport Processing."""

# %%
import re
from collections.abc import Iterator
from itertools import groupby
from pathlib import Path


YEAR_RANGES = {"byr": (1920, 2002), "iyr": (2010, 2020), "eyr": (2020, 2030)}
REQUIRED = YEAR_RANGES.keys() | {"hgt", "hcl", "ecl", "pid"}
HEIGHT_RANGES = {"cm": (150, 193), "in": (59, 76)}
EYE_COLORS = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"}


def passports(text: str) -> Iterator[dict[str, str]]:
    for blank, lines in groupby(text.splitlines(), key=lambda line: not line.strip()):
        if not blank:
            fields = " ".join(lines).split()
            yield dict(field.split(":", 1) for field in fields)


def valid_passport(passport: dict[str, str]) -> bool:
    if not REQUIRED <= passport.keys():
        return False
    for field, (low, high) in YEAR_RANGES.items():
        value = passport[field]
        if not re.fullmatch(r"[0-9]{4}", value) or not low <= int(value) <= high:
            return False
    height = re.fullmatch(r"([0-9]+)(cm|in)", passport["hgt"])
    if height is None:
        return False
    amount, unit = height.groups()
    low, high = HEIGHT_RANGES[unit]
    return (
        low <= int(amount) <= high
        and re.fullmatch(r"#[0-9a-f]{6}", passport["hcl"]) is not None
        and passport["ecl"] in EYE_COLORS
        and re.fullmatch(r"[0-9]{9}", passport["pid"]) is not None
    )


def part1(text: str) -> int:
    return sum(REQUIRED <= passport.keys() for passport in passports(text))


def part2(text: str) -> int:
    return sum(valid_passport(passport) for passport in passports(text))


# %%
if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")

    answer1 = part1(text)
    print(answer1)
    assert answer1 == 226, f"Expected 226, got {answer1}"

    answer2 = part2(text)
    print(answer2)
    assert answer2 == 160, f"Expected 160, got {answer2}"
