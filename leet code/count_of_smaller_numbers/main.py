class Solution:
	global_counter: [int]

	def _increment_counts(self, merged_arr_w_indx: [(int, (int, int))]):
		for indx, each in enumerate(merged_arr_w_indx):
			sorted_pos = each[0]
			
			if sorted_pos != -1:
				increment = indx - sorted_pos
				original_arr_pos = each[1][1]
				self.global_counter[original_arr_pos] += increment
		return

	def _countSmaller(self, nums_w_position: [(int, int)]):
		if len(nums_w_position) <= 1:
			return nums_w_position

		left_arr 	= self._countSmaller(nums_w_position = nums_w_position[ : len(nums_w_position) // 2])
		right_arr 	= self._countSmaller(nums_w_position = nums_w_position[len(nums_w_position) // 2 : ])

		left_arr_w_indx 	= list(zip(range(len(left_arr)), left_arr))
		right_arr_w_indx 	= list(zip([-1 for _ in range(len(right_arr))], right_arr))
		merged_arr_w_indx  	= []

		left_ref 	= 0
		right_ref 	= 0

		while left_ref < len(left_arr) and right_ref < len(right_arr):
			_left 	= left_arr_w_indx[left_ref]
			_right 	= right_arr_w_indx[right_ref]

			if _left[1][0] <= _right[1][0]:
				merged_arr_w_indx.append(_left)
				left_ref += 1

			else:
				merged_arr_w_indx.append(_right)
				right_ref += 1

		if left_ref < len(left_arr):
			merged_arr_w_indx += left_arr_w_indx[left_ref : ]

		if right_ref < len(right_arr):
			merged_arr_w_indx += right_arr_w_indx[right_ref : ]

		self._increment_counts(merged_arr_w_indx = merged_arr_w_indx)
		return list(map(lambda e: e[1], merged_arr_w_indx))

	def countSmaller(self, nums: [int]):
		self.global_counter = [0 for _ in range(len(nums))]
		nums_w_position = list(zip(nums, [i for i in range(len(nums))]))
		self._countSmaller(nums_w_position = nums_w_position)
		return self.global_counter

if __name__ == "__main__":
	nums = [5,2,6,5,5,5,5,1]
	sol = Solution()
	print(sol.countSmaller(nums = nums))
