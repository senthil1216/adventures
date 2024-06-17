#! /usr/bin/env ruby

input = ARGF.read.split("\n").map(&:to_i)
LIMIT = 25

(LIMIT..input.size-1).step(1) do |id|
  invalid_num = input[id]
  if !input.slice(id-LIMIT, LIMIT).combination(2).map(&:sum).include?(invalid_num)
    pp invalid_num
    break
  end
end
