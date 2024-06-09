#!/usr/bin/env ruby

def is_valid_height(passport)
  ht_value = passport["hgt"]
  (ht_value&.end_with?("in") && (59..76).include?(ht_value.to_i)) || 
  (ht_value&.end_with?("cm") && (150..193).include?(ht_value.to_i))
end

def check(passport)
  [
    (1920..2002).include?(passport["byr"]&.to_i),
    (2010..2020).include?(passport["iyr"]&.to_i),
    (2020..2030).include?(passport["eyr"]&.to_i),
    is_valid_height(passport),
    /^#[0-9a-f]{6}$/.match?(passport["hcl"]),
    ["amb","blu","brn","gry","grn","hzl","oth"].include?(passport["ecl"]),
    /^[0-9]{9}$/.match?(passport["pid"])
  ].all?(true)
end

count = 0
ARGF.read.split("\n\n").each do |each_line|
  passport = {}
  each_line.split(" ").each do |k|
    passport[k.split(":")[0].strip] = k.split(":")[1].strip
  end
  count+=1 if check(passport)
end
puts count
