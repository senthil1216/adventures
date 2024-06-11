#! /usr/bin/env ruby

values = []
ARGF.readlines.map(&:strip).each do |line|
  f, b = 0, 127
  r, l = 0, 7
  line.chars.each do |each_char|
    case each_char
    when "F"
      b = b - (b-f)/2 - 1
    when "B"
      f = f + (b-f)/2 + 1
    when "R"
      r = r + (l-r)/2 + 1
    when "L"
      l = l - (l-r)/2 - 1
    end
  end
  values << f * 8 + l
end
puts values.max