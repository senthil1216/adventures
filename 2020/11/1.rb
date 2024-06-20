#!/usr/bin/env ruby

INPUT = []
ARGF.read.split("\n").each do |line|
  INPUT << line.chars
end

row_count = INPUT.size
col_count = INPUT[0].size

def is_valid_dir(r, row_count, c, col_count)
  (0 <= r) && (r <= row_count-1) && (0 <= c) && (c <= col_count-1)
end

def deep_copy(arr)
  new_array = []
  arr.each do |elem|
    row_values = []
    elem.each do |val|
      row_values << val
    end
    new_array << row_values
  end
  new_array
end

prev_seating = deep_copy(INPUT)
while true
  is_seating_changed = false
  new_seating = deep_copy(prev_seating)
  (0..row_count-1).each do |r|
    (0..col_count-1).each do |c|
      dirs = [-1, 0, 1]
      occupied = 0
      dirs.each do |adj_r|
        dirs.each do |adj_c|
          near_r = r+adj_r
          near_c = c+adj_c
          next if !is_valid_dir(near_r, row_count, near_c, col_count)
          next if adj_r == 0 && adj_c == 0
          occupied += 1 if prev_seating[near_r][near_c] == "#"
        end
      end
      
      if occupied == 0 && prev_seating[r][c] == "L"
        new_seating[r][c] = "#"
        is_seating_changed = true
      elsif occupied >= 4 && prev_seating[r][c] == "#"
        new_seating[r][c] = "L"
        is_seating_changed = true
      end
    end
  end
  prev_seating = deep_copy(new_seating)
  break if !is_seating_changed
end
ans = 0
(0..prev_seating.size-1).each do |r|
  (0..prev_seating[0].size-1).each do |c|
    ans+= 1 if prev_seating[r][c] == "#"
  end
end
pp ans