#! /usr/bin/env ruby

input = ARGF.read.split("\n").map(&:to_i)
LIMIT = 25

invalid_num = 0

(LIMIT..input.size-1).step(1) do |id|
  found_valid_sum = false
  numSet = Set.new
  invalid_num = input[id]
  (id-1).downto(id-LIMIT).each do |prev_id|
    numSet.add(input[id] - input[prev_id])
  end
  (id-1).downto(id-LIMIT).each do |prev_id|
    if numSet.include?(input[prev_id])
      found_valid_sum = true
      break
    end
  end
  break if !found_valid_sum
end

pp invalid_num