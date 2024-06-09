#! /usr/bin/env ruby

count, col = 0, 0
ARGF.each_line do |line|
  line = line.strip
  count += 1 if line[col] == "#"
  col += 3
  col = (col - line.size) if col >= line.size
end
puts count