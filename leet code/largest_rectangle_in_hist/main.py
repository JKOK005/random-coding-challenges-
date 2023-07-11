"""
Solution accepted at https://leetcode.com/problems/largest-rectangle-in-histogram/submissions/991457933/
"""

class Solution:
    def smallest_num_to_left(self, heights: [int]) -> [int]:
        stack   = []
        resp    = []

        for indx, each_height in enumerate(heights):
            if len(stack) == 0:
                resp.append(-1)
                stack.append(indx)

            else:
                head_indx = stack.pop()

                while heights[head_indx] >= each_height and len(stack) > 0:
                    head_indx = stack.pop()

                if heights[head_indx] >= each_height:
                    resp.append(-1)
                    stack.append(indx)

                else:
                    resp.append(head_indx)
                    stack.append(head_indx)
                    stack.append(indx)
        return resp

    def smallest_num_to_right(self, heights: [int]) -> [int]:
        resp                    = []
        disp                    = []
        reversed_heights        = heights[::-1]
        smallest_indx_to_left   = self.smallest_num_to_left(heights = reversed_heights)

        for indx in range(len(heights)):
            if smallest_indx_to_left[indx] == -1:
                disp.append(-1)

            else:
                disp.append(indx - smallest_indx_to_left[indx])

        disp = disp[::-1]

        for indx in range(len(heights)):
            if disp[indx] == -1:
                resp.append(-1)

            else:
                resp.append(indx + disp[indx])
        return resp

    def largestRectangleArea(self, heights: [int]) -> int:
        max_area = 0
        smallest_indx_to_left   = self.smallest_num_to_left(heights = heights)
        smallest_indx_to_right  = self.smallest_num_to_right(heights = heights)

        for indx, each_height in enumerate(heights):
            left_bound  = smallest_indx_to_left[indx]
            right_bound = smallest_indx_to_right[indx] if smallest_indx_to_right[indx] > 0 else len(heights)
            max_area    = max(max_area, (right_bound - left_bound -1) * each_height)
        return max_area


if __name__ == "__main__":
    sol = Solution()
    lst = [2,1,5,6,2,3]

    # print(sol.smallest_num_to_left(heights = lst))
    print(sol.largestRectangleArea(heights = lst))