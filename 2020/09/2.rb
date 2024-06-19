#! /usr/bin/env ruby

input = ARGF.read.split("\n").map(&:to_i)
LIMIT = 25

invalid_num = 0
(LIMIT..input.size-1).step(1) do |id|
  invalid_num = input[id]
  if !input.slice(id-LIMIT, LIMIT).combination(2).map(&:sum).include?(invalid_num)
    break
  end
end

idx = 2
found = false
while idx < input.length
    curr_arr = input.each_cons(idx).find do |curr_arr|
        curr_arr.sum == invalid_num
    end
    if curr_arr
        pp curr_arr.min + curr_arr.max
        break
    end
    idx += 1
end
