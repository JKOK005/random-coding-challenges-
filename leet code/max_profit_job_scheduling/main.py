"""
Leet code: https://leetcode.com/problems/maximum-profit-in-job-scheduling/

Solution passes 29 / 30 test cases
"""

class Solution:
    def jobScheduling(self, startTime: [int], endTime: [int], profit: [int]) -> int:
        jobs_map = {}

        for indx, each_start_time in enumerate(startTime):
            if each_start_time -1 not in jobs_map:
                jobs_map[each_start_time -1] = [(endTime[indx] -1, profit[indx])]

            else:
                jobs_map[each_start_time -1] += [(endTime[indx] -1, profit[indx])]

        latest_end_time     = max(endTime) -1
        total_profits       = [-1 for _ in range(latest_end_time +1)]

        for indx in range(len(total_profits) -1, -1, -1):
            possible_jobs_to_run = jobs_map.get(indx, [])
            max_possible_profits = total_profits[indx +1] if indx +1 <= latest_end_time else 0  # Dont run any jobs

            for (job_end, job_profits) in possible_jobs_to_run:
                max_possible_profits = max(max_possible_profits, total_profits[job_end] + job_profits)
            total_profits[indx] = max_possible_profits
        return total_profits[0]

if __name__ == "__main__":
    startTime   = [2,2,3,4,6]
    endTime     = [3,5,10,6,9]
    profit      = [20,20,100,70,60]

    sol = Solution()
    print(sol.jobScheduling(startTime = startTime, endTime = endTime, profit = profit))