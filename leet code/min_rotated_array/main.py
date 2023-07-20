class Solution:
    def _findMin(self, nums: int, start: int, end: int):
        if start >= end -1:
            return min(nums[start], nums[end])

        middle = (end - start) // 2 + start

        if nums[end] > nums[middle]:
            # min exists between middle to start 
            return self._findMin(nums = nums, start = start, end = middle)

        elif nums[end] < nums[middle]:
            return self._findMin(nums = nums, start = middle, end = end) 

        else:
            if nums[start] == nums[end]:
                return nums[middle]

            return self._findMin(nums = nums, start = start, end = middle)

    def findMin(self, nums: int) -> int:
        return self._findMin(nums = nums, start = 0, end = len(nums) -1)

def rotate(l, y=1):
    if len(l) == 0:
        return l
    y = -y % len(l)     # flip rotation direction
    
    return l[y:] + l[:y]

if __name__ == "__main__":
    import random

    count           = 1000
    random_rot_num  = random.randint(0, count)

    sol             = Solution()
    nums            = [random.randint(0, 100) for _ in range(10)]
    min_num         = min(nums)

    nums            = sorted(nums)
    nums            = rotate(l = nums, y = random_rot_num)

    calc_min_num    = sol.findMin(nums = nums)
    print(calc_min_num, min_num)