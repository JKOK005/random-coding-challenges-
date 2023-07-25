import numpy as np
import sys

class Solution:
    def fetchCount(self, indx_pos, w_indx, h_indx, w_indx_bounds, h_indx_bounds):
        if w_indx < w_indx_bounds[0] or w_indx > w_indx_bounds[1] or h_indx < h_indx_bounds[0] or h_indx > h_indx_bounds[1]:
            return 0 
        return indx_pos[w_indx, h_indx]

    def maxEnvelopes(self, envelopes: [[int]]) -> int:
        min_env_width   = sys.maxsize
        max_env_width   = 0
        min_env_height  = sys.maxsize
        max_env_height  = 0

        for each_envelope in envelopes:
            env_width, env_height = each_envelope
            min_env_width   = min(min_env_width, env_width)
            max_env_width   = max(max_env_width, env_width)
            min_env_height  = min(min_env_height, env_height)
            max_env_height  = max(max_env_height, env_height)

        indx_pos        = np.zeros([max_env_width -min_env_width +1, max_env_height -min_env_height +1])
        valid_widths    = set()
        valid_heights   = set()

        for each_envelope in envelopes:
            env_width, env_height = each_envelope
            indx_pos[env_width - min_env_width, env_height - min_env_height] = 1 
            valid_widths.add(env_width - min_env_width)
            valid_heights.add(env_height - min_env_height)

        indx_pos = np.take(indx_pos, sorted(valid_widths), axis = 0)
        indx_pos = np.take(indx_pos, sorted(valid_heights), axis = 1)
        solution  = np.zeros(indx_pos.shape)

        for w_indx in range(len(solution)):
            for h_indx in range(len(solution[0])):
                solution[w_indx, h_indx] = max(
                                            self.fetchCount(indx_pos = solution, w_indx = w_indx -1, h_indx = h_indx, 
                                                            w_indx_bounds = [0, indx_pos.shape[0]], h_indx_bounds = [0, indx_pos.shape[1]]),

                                            self.fetchCount(indx_pos = solution, w_indx = w_indx, h_indx = h_indx -1, 
                                                            w_indx_bounds = [0, indx_pos.shape[0]], h_indx_bounds = [0, indx_pos.shape[1]]),

                                            self.fetchCount(indx_pos = indx_pos, w_indx = w_indx, h_indx = h_indx, 
                                                            w_indx_bounds = [0, indx_pos.shape[0]], h_indx_bounds = [0, indx_pos.shape[1]]) + \
                                            self.fetchCount(indx_pos = solution, w_indx = w_indx -1, h_indx = h_indx -1, 
                                                            w_indx_bounds = [0, indx_pos.shape[0]], h_indx_bounds = [0, indx_pos.shape[1]])
                                        )
        return int(solution[-1, -1])

if __name__ == "__main__":
    envelopes   = [[30,50],[12,2],[3,4],[12,15]]
    sol         = Solution()
    resp        = sol.maxEnvelopes(envelopes = envelopes)
    print(resp)