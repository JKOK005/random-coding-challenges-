class Solution:
    def check_in_bounds(self, prev_bounds, cur_bounds) -> bool:
        return prev_bounds[0] >= cur_bounds[0] and prev_bounds[1] <= cur_bounds[1]

    def minTaps(self, n: int, ranges: [int]) -> int:
        queue = []

        for indx, tap_range in enumerate(ranges):
            if (tap_range > 0):
                coverage = (max(0, indx - tap_range), min(n, indx + tap_range))

                if len(queue) == 0:
                    queue.append(coverage)
                
                else:
                    prev_bounds = queue.pop()

                    while self.check_in_bounds(prev_bounds = prev_bounds, cur_bounds = coverage) and len(queue) > 0:
                        prev_bounds = queue.pop()

                    if self.check_in_bounds(prev_bounds = prev_bounds, cur_bounds = coverage):
                        queue.append(coverage)

                    elif self.check_in_bounds(prev_bounds = coverage, cur_bounds = prev_bounds):
                        queue.append(prev_bounds)

                    else:
                        queue.append(prev_bounds)
                        queue.append( (max(prev_bounds[1], coverage[0]), coverage[1]) )

        is_cont             = True
        prev_right_bound    = 0

        for each_queue in queue:
            left_bound          = each_queue[0]
            right_bound         = each_queue[1]
            is_cont             = is_cont and left_bound <= prev_right_bound +1
            prev_right_bound    = right_bound

        return len(queue) if len(queue) > 0 and is_cont and queue[-1][-1] == n else -1

if __name__ == "__main__":
    sol = Solution()
    n = 3
    ranges = [1,0,0,1]

    res = sol.minTaps(n = n, ranges = ranges)
    print(res)