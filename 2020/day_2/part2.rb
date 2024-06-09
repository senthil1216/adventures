#! /usr/bin/env ruby

count = 0
input = ARGF.each_line do |line|
  rangeStr, char, password = line.strip.split(" ")
  password_char = char[0].strip
  min, max = rangeStr.split("-").map(&:to_i)
  count += 1 if (password[min-1] == password_char)  ^ (password[max-1] == password_char)
end
pp count