# Test passes 171 / 175 cases

class Solution:
    def sort_nums(self, nums: [int], divisor: int, valid_start: int, valid_end: int) -> [int]:
        if divisor == 0:
            return

        tmp_counter     = {i : 0  for i in range(10)}
        counter         = {i : 0  for i in range(10)}
        start_range     = {i : 0 for i in range(10)}
        end_range       = {i : 0 for i in range(10)}

        for each_num in nums[valid_start : valid_end +1]:
            counter[(each_num // divisor) % 10] += 1

        _start = valid_start
        for each_key, each_val in counter.items():
            start_range[each_key] = _start
            end_range[each_key]   = _start + counter[each_key] -1
            _start += each_val

        indx = valid_start
        while indx < valid_end:
            cur_num = nums[indx]
            digit   = (cur_num // divisor) % 10

            if tmp_counter[digit] < counter[digit] and (indx < start_range[digit] or indx > end_range[digit]):
                _tmp = nums[start_range[digit] + tmp_counter[digit]]
                nums[start_range[digit] + tmp_counter[digit]] = cur_num
                nums[indx] = _tmp
                tmp_counter[digit] += 1

            else:
                indx += 1

        for each_key in start_range.keys():
            if start_range[each_key] < end_range[each_key]:
                self.sort_nums(nums = nums, divisor = divisor // 10, valid_start = start_range[each_key], valid_end = end_range[each_key])
        return

    def sort_positive(self, nums: [int]) -> int:
        indx        = 0        
        end_indx    = len(nums) -1

        while indx < end_indx:
            if nums[indx] > 0:
                _tmp = nums[end_indx]
                nums[end_indx] = nums[indx]
                nums[indx] = _tmp    
                end_indx -= 1

            else:
                indx += 1

        return end_indx

    def firstMissingPositive(self, nums: [int]) -> int:
        if len(nums) > 1:
            start_indx = self.sort_positive(nums = nums)
            self.sort_nums(nums = nums, divisor = 10 ** 9, valid_start = start_indx, valid_end = len(nums))

            if not nums[start_indx] in [0, 1]:
                return 1

            for offset, each_pos_num in enumerate(nums[start_indx : ]):
                if offset < len(nums[start_indx : ]) - 1 and nums[start_indx + offset + 1] - each_pos_num > 1:
                    break

            return nums[start_indx + offset] + 1

        else:
            return 1 if nums[0] != 1 else 2
        
if __name__ == "__main__":
    sol = Solution()
    # nums = [0,2,2,1,1]
    nums = [i for i in range(100, -100, -2)]
    # start_indx = sol.sort_positive(nums = nums)
    # sol.sort_nums(nums = nums, divisor = 1000, valid_start = start_indx, valid_end = len(nums))
    # print(nums)
    min_pos_num = sol.firstMissingPositive(nums = nums)
    print(nums)
    print(min_pos_num)