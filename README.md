# Advent of Code

Standalone Python solutions for Advent of Code. Use Python 3.10 or newer.

## Leaderboard

Use join code `5134308-3d999e7d` to join our Advent of Code private leaderboard.

## Run a solution

Run a day's script directly from the repository root.

```sh
python3 2024/03/solve.py
```

Each script reads the `input` file beside it and prints the answer for each
available part in order. Where an expected answer is recorded, an assertion
checks it and shows the expected and actual values if they differ.

## Work interactively

Open a `solve.py` file in VS Code with the Python and Jupyter extensions.
Use **Run Cell** to run the `# %%` cells in order. To try a sample, call a
part directly with sample text in a new cell.

```python
# %%
sample = "mul(2,3)"
answer = part1(sample)
print(answer)
assert answer == 6
```

Use **Debug Cell** with a breakpoint to step through a calculation.

## Start a puzzle

Create `YEAR/DD/solve.py` and save the puzzle input beside it as `input`.
Copy the cell layout from an existing day. Write `part1(text)` and
`part2(text)` as you solve them, then add an assertion for each known answer.

## Solutions

| Year | Days |
| --- | --- |
| 2020 | [01](2020/01/solve.py), [02](2020/02/solve.py), [03](2020/03/solve.py), [04](2020/04/solve.py), [05](2020/05/solve.py), [06](2020/06/solve.py), [07](2020/07/solve.py), [08](2020/08/solve.py), [09](2020/09/solve.py), [10](2020/10/solve.py), [11](2020/11/solve.py) |
| 2021 | [01](2021/01/solve.py), [02](2021/02/solve.py), [03](2021/03/solve.py), [04](2021/04/solve.py), [05](2021/05/solve.py), [06](2021/06/solve.py), [07](2021/07/solve.py), [08](2021/08/solve.py), [09](2021/09/solve.py), [10](2021/10/solve.py), [11](2021/11/solve.py), [12](2021/12/solve.py), [13](2021/13/solve.py), [14](2021/14/solve.py) |
| 2022 | [01](2022/01/solve.py), [02](2022/02/solve.py), [03](2022/03/solve.py), [04](2022/04/solve.py), [05](2022/05/solve.py), [06](2022/06/solve.py), [07](2022/07/solve.py), [08](2022/08/solve.py), [09](2022/09/solve.py), [10](2022/10/solve.py), [11](2022/11/solve.py), [12](2022/12/solve.py), [13](2022/13/solve.py), [14](2022/14/solve.py), [15](2022/15/solve.py), [17](2022/17/solve.py), [18](2022/18/solve.py) |
| 2023 | [01](2023/01/solve.py) |
| 2024 | [01](2024/01/solve.py), [02](2024/02/solve.py), [03](2024/03/solve.py) |
| 2025 | [Day 1 · Secret Entrance](2025/01/solve.py), [Day 2 · Gift Shop](2025/02/solve.py) |
