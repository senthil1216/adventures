#!/usr/bin/env ruby

INPUT = (ARGF.readlines.map(&:strip).map(&:to_i) + [0]).sort

mem = {}
def dfs(curr_idx, mem)
    return 1 if curr_idx == INPUT.size-1
    res = 0
    return mem[curr_idx] if mem.has_key?(curr_idx)
    (curr_idx+1..INPUT.size-1).each do |i|
        if (INPUT[i] - INPUT[curr_idx]) <= 3
            res += dfs(i, mem)
        end
    end
    mem[curr_idx] = res
    res
end

pp dfs(0, mem)