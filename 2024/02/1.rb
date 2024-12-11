#! /usr/bin/env ruby

input = ARGF.each_line.map(&:split).map { |line| line.map(&:to_i) }

pp (input.count do |line|
  is_valid_order = (line == line.sort || line == line.sort.reverse)
  is_safe = true
  line.each_cons(2).each do |a, b|
    is_safe &= [1, 2, 3].include?((a - b).abs)
  end
  is_valid_order && is_safe
end)
