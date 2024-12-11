#! /usr/bin/env ruby

input = ARGF.each_line.map(&:split).map { |line| line.map(&:to_i) }

def is_safe?(numbers)
  is_valid_order = (numbers == numbers.sort || numbers == numbers.sort.reverse)
  is_safe = true
  numbers.each_cons(2).each do |a, b|
    is_safe &= [1, 2, 3].include?((a - b).abs)
  end
  return is_valid_order && is_safe
end

pp (input.count do |line|
  is_valid = false
  line.combination(line.size - 1).each do |items|
    is_valid |= is_safe?(items)
  end
  if is_valid
  end
  is_valid
end)
