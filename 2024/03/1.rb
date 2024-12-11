#! /usr/bin/env ruby

input = ARGF.read

pp input.scan(/\mul\(\d+\,\d+\)/).map { |m| m.scan(/\d+/).map(&:to_i) }.sum { |a, b| a * b }
