# Accepted in leetcode with fastest runtime (241 ms) across all submissions 
# Ref: https://leetcode.com/problems/heaters

import sys

class Solution:
    def _findRadius(self, houses: [int], heaters: [int]) -> int:
        house_indx  = 0
        heater_indx = 0
        min_radius  = -1

        while house_indx < len(houses) and heater_indx < len(heaters):
            cur_radius  = abs(houses[house_indx] - heaters[heater_indx])
            next_radius = abs(houses[house_indx] - heaters[heater_indx +1]) if heater_indx +1 < len(heaters) else sys.maxsize

            if next_radius <= cur_radius:
                heater_indx += 1

            else:
                min_radius = max(min_radius, cur_radius)
                house_indx += 1

        return min_radius

    def findRadius(self, houses: [int], heaters: [int]) -> int:
        return self._findRadius(houses = sorted(houses), heaters = sorted(heaters))

if __name__ == "__main__":
    sol = Solution()
    houses = [1,2,3,4]
    heaters = [1,4]

    res = sol.findRadius(houses = houses, heaters = heaters)
    print(res)