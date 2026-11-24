# %%
from pathlib import Path

def part1(text: str) -> int:
    values = text.split(",")
    count = 0
    for v in values:
        start, end = [int(x) for x in v.split("-")]
        for num in range(start, end+1):
            str_num = str(num)
            str_len = len(str_num)
            if str_num[:str_len//2] == str_num[str_len//2:]:
                count += num
    return count


def part2(text: str) -> int:
    values = text.split(",")
    count = 0
    invalids= set()
    for v in values:
        start, end = [int(x) for x in v.split("-")]
        for num in range(start, end+1):
            s = str(num)
            str_len = len(s)
            for n in range(1, (str_len//2)+1):
                result = [s[i:i+n] for i in range(0, len(s), n)]
                if all(item == result[0] for item in result):
                    invalids.add(num)
    count = sum(invalids)
    return count

if __name__ == "__main__":
    text = Path(__file__).with_name("input").read_text(encoding="utf-8")
    print(part1(text))
    print(part2(text))


# %%
