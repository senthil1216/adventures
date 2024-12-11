#! /usr/bin/env ruby

input = ARGF.each_line.map(&:split)
left = []
right = []
input.each do |line|
  left << line[0].to_i
  right << line[1].to_i
end
left.sort!
right.sort!
pp left
pp right
sum = 0
(0...left.length).each do |i|
  sum += (left[i] - right[i]).abs
end
pp sum
