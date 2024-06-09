#! /usr/bin/env ruby

input = ARGF.each_line.map(&:to_i)
input.combination(2).each do |nums|
  if nums.sum == 2020
    pp nums[0] * nums[1]
  end
end