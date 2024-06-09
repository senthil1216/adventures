#! /usr/bin/env ruby

valid_password_count = 0
input = ARGF.each_line do |line|
  rules = line.strip.split(" ")
  rangeStr = rules[0]
  password_char = rules[1][0]
  password = rules[2]
  ranges = rangeStr.split("-").map(&:to_i)
  first_idx = ranges[0]-1
  second_idx = ranges[1]-1
  if password[first_idx] == password_char && password[second_idx] != password_char
    valid_password_count += 1
  elsif password[first_idx] != password_char && password[second_idx] == password_char
    valid_password_count += 1
  end
end
pp valid_password_count