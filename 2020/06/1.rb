#! /usr/bin/env ruby

puts (ARGF.read.split("\n\n").map do |each|
  each.split("\n").join("").chars.uniq.size
end).sum