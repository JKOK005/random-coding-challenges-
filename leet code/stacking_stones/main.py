class Solution():
	def countStones(self, arr: [int], start: int, end: int):
		if len(arr[start : end]) == 0:
			return 0

		elif len(arr[start : end]) == 1: 
			if arr[start] == 0:
				return 0
			else:
				return 1

		elif len(arr[start : end]) == 2:
			if arr[start] == arr[end -1]:
				return 1
			else:
				return 2

		min_indx 	= []
		min_height 	= arr[start]

		for indx in range(start, end):
			cur_height = arr[indx]

			if cur_height < min_height:
				min_height = cur_height
				min_indx = [indx]

			elif cur_height == min_height:
				min_indx.append(indx)

		min_indx = [start -1] + min_indx + [end]
		total_stones = 1
		for indx in range(len(min_indx) -1):
			total_stones += self.countStones(arr, start = min_indx[indx] +1, end = min_indx[indx +1])

		return total_stones

if __name__ == "__main__":
	sol = Solution()
	arr = [8,8,5,7,9,8,7,4,8]
	print(sol.countStones(arr, start = 0, end = len(arr)))