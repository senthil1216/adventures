#! /usr/bin/env ruby

puts (ARGF.read.split("\n\n").map do |each|
  groups = each.split("\n")
  char_count = Hash.new(0)
  groups.join("").to_s.each_char do |c|
    char_count[c] += 1
  end
  char_count.values.count(groups.size)
end).sum
