#! /usr/bin/env ruby

def find_trees(lines, rIdx, cIdx, rowCount, colCount)
  count = 0
  r,c = 0, 0
  while r < rowCount
    count += 1 if lines[r][c] == "#"
    r += rIdx
    c += cIdx
    c = (c - colCount) if c >= colCount
  end
  count
end

lines = []
ARGF.each_line do |line|
  lines << line.strip
end
puts find_trees(lines, 1, 1, lines.size, lines[0].size) * 
  find_trees(lines, 1, 3, lines.size, lines[0].size) * 
  find_trees(lines, 1, 5, lines.size, lines[0].size) * 
  find_trees(lines, 1, 7, lines.size, lines[0].size) * 
  find_trees(lines, 2, 1, lines.size, lines[0].size)

