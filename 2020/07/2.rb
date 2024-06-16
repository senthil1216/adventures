#! /usr/bin/env ruby

no_other = Set.new
bags = {}
ARGF.read.split("\n").each do |line|
  line = line.gsub!(/bags contain /,",").gsub!(/(?:bag)s?.?/, ",")
  if line.include?("no other")
    no_other.add(line.split(",")[0].strip!)
  else
    splits = line.split(",")
    key = splits[0].strip!
    contain = {}
    splits[1..splits.size-1].each do |val|
      contain_bag_val = /\d+/.match(val)[0].to_i
      contain_bag_name = val.gsub!(contain_bag_val.to_s, "").strip!
      contain[contain_bag_name] = contain_bag_val
    end
    bags[key] = contain
  end
end

def find_contains(bags, key, no_other)
  return 0 if no_other.include?(key)
  return bags[key].map do |ik, iv|
    iv + iv * find_contains(bags, ik, no_other)
  end.sum
end
puts (bags["shiny gold"].map do |key, val|
  val + val * find_contains(bags, key, no_other)
end.sum)