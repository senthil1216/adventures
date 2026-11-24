# Advent of Code

Standalone Python solutions for Advent of Code. Use Python 3.10 or newer.

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
| 2020 | [01](2020/01/solve.py), [02](2020/02/solve.py), [03](2020/03/solve.py), [04](2020/04/solve.py), [05](2020/05/solve.py), [06](2020/06/solve.py), [07](2020/07/solve.py), [08](2020/08/solve.py), [09](2020/09/solve.py), [10](2020/10/solve.py), [11 (part 1)](2020/11/solve.py) |
| 2024 | [01](2024/01/solve.py), [02](2024/02/solve.py), [03](2024/03/solve.py) |
| 2025 | [Day 2 · Gift Shop](2025/01/solve.py) |
