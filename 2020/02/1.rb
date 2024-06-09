#! /usr/bin/env ruby

valid_password_count = 0
input = ARGF.each_line do |line|
  rangeStr, char, password = line.strip.split(" ")
  password_char = char[0].strip
  count = password.count(password_char)
  min, max = rangeStr.split("-").map(&:to_i)
  valid_password_count += 1 if count >= min && count <= max
end
pp valid_password_count == 638