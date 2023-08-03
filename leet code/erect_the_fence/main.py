import sys

class Solution:
    def append_upper(self, fence, coord):    
        while True:
            if len(fence) <= 1:
                fence.append(coord)
                break

            else:
                last_point      = fence[-1]
                last_last_point = fence[-2]

                current_angle   = (coord[1] - last_point[1]) / (coord[0] - last_point[0])
                last_angle      = (last_point[1] - last_last_point[1]) / (last_point[0] - last_last_point[0])

                if current_angle > last_angle:
                    fence.pop()

                else:
                    fence.append(coord)
                    break
        return

    def append_lower(self, fence, coord):    
        while True:
            if len(fence) <= 1:
                fence.append(coord)
                break

            else:
                last_point      = fence[-1]
                last_last_point = fence[-2]

                current_angle   = (last_point[1] - coord[1]) / (coord[0] - last_point[0])
                last_angle      = (last_last_point[1] - last_point[1]) / (last_point[0] - last_last_point[0])

                if current_angle > last_angle:
                    fence.pop()

                else:
                    fence.append(coord)
                    break
        return

    def outerTrees(self, trees: [[int]]) -> [[int]]:
        sorted_trees      = sorted(trees, key = lambda x: (x[0], x[1]))

        min_coord         = None 
        max_coord         = None
        upper_fence_queue = []
        lower_fence_queue = []
        left_right_bounds = []
        indx = 0

        while indx < len(sorted_trees):
            if sorted_trees[indx][0] == sorted_trees[0][0] or sorted_trees[indx][0] == sorted_trees[-1][0]:
                left_right_bounds.append(sorted_trees[indx])

            if min_coord == None:
                min_coord = sorted_trees[indx]

            if indx == len(sorted_trees) -1 or sorted_trees[indx][0] != sorted_trees[indx +1][0]:
                max_coord = sorted_trees[indx]

            if min_coord is not None and max_coord is not None:
                self.append_upper(fence = upper_fence_queue, coord = max_coord)
                self.append_lower(fence = lower_fence_queue, coord = min_coord)
                min_coord = None 
                max_coord = None

            indx += 1
        return set(tuple(x) for x in upper_fence_queue + lower_fence_queue + left_right_bounds)

if __name__ == "__main__":
    sol     = Solution()
    trees   = [[1,2],[2,2],[4,2]]
    res     = sol.outerTrees(trees = trees)
    print(res)