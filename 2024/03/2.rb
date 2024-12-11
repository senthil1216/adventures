#! /usr/bin/env ruby

input = ARGF.read

enabled = true
disabled = false
sum = 0
input.scan(/don.t\(\)|do\(\)|mul\(\d+\,\d+\)/).each do |item|
  value = item.scan(/mul\(\d+\,\d+\)/)
  if enabled && value && value.first
    a, b = value.first.scan(/\d+/).map(&:to_i)
    sum += a * b
  else
    enabled = item.scan(/don.t\(\)/).empty?
    disabled = item.scan(/do\(\)/).empty?
    enabled = !disabled
  end
end

pp sum
