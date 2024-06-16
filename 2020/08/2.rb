#! /usr/bin/env ruby


inst_set = []

ARGF.read.split("\n").each do |curr_inst|
  curr = curr_inst.split(" ")
  inst_set << [curr[0], curr[1].to_i]
end

def calculate(inst_set)
  acc = 0
  s = Set.new
  i = 0
  while (i < inst_set.size)
    return [false, 0] if s.include?(i)
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
  return [true, acc]
end

inst_set.each_index do |id|
  if inst_set[id][0] == "jmp"
    dup_inst_set = inst_set.dup
    dup_inst_set[id] = ["nop", dup_inst_set[id][1]]
    is_valid, score = calculate(dup_inst_set)
    if is_valid
      pp score
      break
    end
  end
end

