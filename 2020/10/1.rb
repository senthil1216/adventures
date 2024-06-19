#!/usr/bin/env ruby

input = (ARGF.readlines.map(&:strip).map(&:to_i) + [0]).sort
diff_1 = 0
diff_3 = 0
input.each_cons(2).each do |curr|
    curr_diff = curr[1] - curr[0]
    diff_1 += 1 if curr_diff == 1
    diff_3 += 1 if curr_diff == 3
end
pp diff_1 * (diff_3 + 1)