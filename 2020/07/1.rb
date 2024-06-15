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

def find_bags(key, bag)
  return true if  bag[key].include?("shiny gold")
  bag[key].each do |s|
    next if !bag.has_key?(s)
     if find_bags(s, bag)
      return true
    end
  end
  return false
end

count = 0
bags.map do |key, values|
  if find_bags(key, bags)
    count += 1
  end
end
puts count