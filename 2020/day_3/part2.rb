#! /usr/bin/env ruby

def find_trees(lines, row_step, col_step)
  rowCount, colCount = lines.size, lines[0].size
  count, r,c = 0,0,0
  while r < rowCount
    count += 1 if lines[r][c] == "#"
    r += row_step
    c += col_step
    c = (c - colCount) if c >= colCount
  end
  count
end

lines = []
ARGF.each_line do |line|
  lines << line.strip
end
puts find_trees(lines, 1, 1) * 
  find_trees(lines, 1, 3) * 
  find_trees(lines, 1, 5) * 
  find_trees(lines, 1, 7) * 
  find_trees(lines, 2, 1)

