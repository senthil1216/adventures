#! /usr/bin/env ruby

require 'set'

bags = {}

ARGF.read.split("\n").each do |line|
  line = line.gsub!(/bags contain \d{0,}/,",")
  line = line.gsub!(/(?:bag)s?.?/, ",")
  line = line.split(",")
  key = line[0].strip!
  values = line[1..line.size-1]
  s = Set.new
  values.each do |v|
    s.add(v.gsub!(/(?:\d{0,})/,"").strip!)
  end
  bags[key] = s
end
def find_bags(key, bag, final_list)
  return true if  bag[key].include?("shiny gold")
  return true if final_list.include?(key)
  bag[key].each do |s|
    if bag.has_key?(s) && find_bags(s, bag, final_list)
      final_list.add(key)
      final_list.add(s)
    end
  end
  return false
end

final_list = Set.new
# pp bags
bags.map do |key, _|
  find_bags(key, bags, final_list)
end

puts final_list.size
# 233