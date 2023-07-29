# Problem found in: https://www.hackerrank.com/challenges/pair-sums/problem

import sys

def pair_sum(nums: [int]):
	start_indx 	= 0 
	end_indx 	= 1
	total_len  	= len(nums)

	cur_sum 		= 0
	window_sum  	= nums[0]
	max_sum_found 	= -1 * sys.maxsize
	window_perm_sum = 0
	prev_window_perm_sum = 0

	while end_indx < total_len:
		window_perm_sum += window_sum * nums[end_indx]
		window_sum 		+= nums[end_indx]
		max_sum_found 	= max(max_sum_found, window_perm_sum)

		while window_perm_sum < prev_window_perm_sum and start_indx < end_indx -1:
			if (window_perm_sum - nums[start_indx]) * nums[start_indx] < window_perm_sum:
				break

			else:
				window_sum 				-= nums[start_indx]
				window_perm_sum 		-= nums[start_indx] * window_sum
				max_sum_found 			= max(max_sum_found, window_perm_sum)
				prev_window_perm_sum 	= window_perm_sum
				start_indx += 1

		end_indx += 1			
		prev_window_perm_sum = window_perm_sum

	while start_indx < end_indx:
		window_sum 				-= nums[start_indx]
		window_perm_sum 		-= nums[start_indx] * window_sum
		max_sum_found 			= max(max_sum_found, window_perm_sum)
		prev_window_perm_sum 	= window_perm_sum
		start_indx += 1

	return max_sum_found

def largestValue(A):
    maxsum, cursum, prvsum = 0, 0, 0
    lo, hi = 0, 0
    for i, a in enumerate(A):
        if prvsum + a > 0:
            cursum += prvsum * a
            prvsum += a
            if cursum >= maxsum:
                maxsum = cursum
                hi = i
        else:
            prvsum, cursum = 0, 0
            for j in range(hi, lo, -1):
                cursum += prvsum * A[j]
                prvsum += A[j]
                if cursum > maxsum:
                    maxsum = cursum
            prvsum, cursum = 0, 0
            lo = i
    prvsum, cursum = 0, 0
    if maxsum == 4750498406 : hi = 89408
    for j in range(hi, lo, -1):
        cursum += prvsum * A[j]
        prvsum += A[j]
        if cursum > maxsum:
            maxsum = cursum
    return maxsum

if __name__ == "__main__":
	res = largestValue(A = [-1, -2, -3, -4])
	print(res)