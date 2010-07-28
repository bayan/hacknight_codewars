require 'mathn'

class Prime
  def self.find(n)
    primes = Prime.new
    (n-1).times {|i| primes.succ }
    primes.succ
  end
end