#! /usr/bin/env ruby

input = ARGF.each_line.map(&:split)
left = []
right = []
input.each do |line|
  left << line[0].to_i
  right << line[1].to_i
end

right_counts = right.each_with_object(Hash.new(0)) do |num, counts|
  counts[num] += 1
end
sum = 0
left.each do |num|
  sum += (num * right_counts[num])
end
pp sum
