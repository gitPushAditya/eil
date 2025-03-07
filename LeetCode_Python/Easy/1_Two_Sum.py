"""Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order."""
from typing import List
import cProfile


def two_sum(nums: List[int], target: int) -> List[int]:
    index_map = {}
    for i, num in enumerate(nums):
        difference = target - num
        if difference in index_map:
            return [index_map[difference], i]
        index_map[num] = i



numbers = [3,2,4]
target_value = 6

output = two_sum(numbers, target_value)
print(output)

cProfile.run('two_sum(numbers, target_value)')
