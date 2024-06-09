#! /usr/bin/env ruby

valid_password_count = 0
input = ARGF.each_line do |line|
  rules = line.strip.split(" ")
  rangeStr = rules[0]
  password_char = rules[1][0]
  password = rules[2]
  count = 0
  password.each_char do |ch|
    if ch == password_char
      count+=1
    end
  end
  ranges = rangeStr.split("-").map(&:to_i)
  min = ranges[0]
  max = ranges[1]
  # pp count, password_char, min, max
  if count >= min && count <= max
    valid_password_count += 1
  end
end
pp valid_password_count