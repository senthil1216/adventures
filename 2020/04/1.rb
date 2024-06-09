#! /usr/bin/env ruby

def check_valid_passport(keys)
  return true if keys.size == 8
  return keys.size == 7 && !keys.include?("cid")
  return false
end

count = 0
ARGF.read.split("\n\n").each do |each_line|
  keys = []
  each_line.split(" ").each do |k|
    keys << k.split(":")[0]
  end
  count += 1 if check_valid_passport(keys)
end
puts count