#! /usr/bin/env ruby


inst_set = []

ARGF.read.split("\n").each do |curr_inst|
  curr = curr_inst.split(" ")
  inst_set << [curr[0], curr[1].to_i]
end
acc = 0
s = Set.new
i = 0
while (i < inst_set.size)
  break if s.include?(i)
  s.add(i)
  curr_inst = inst_set[i][0]
  curr_val = inst_set[i][1]
  acc += curr_val if curr_inst == "acc"
  if curr_inst == "jmp"
    i += curr_val
  else
    i += 1
  end
end
pp acc