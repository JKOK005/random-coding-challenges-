class Solution:
    def merge(self, arr_A, arr_B, transition_state):
        if transition_state == -1:
            return arr_A + arr_B

        elif transition_state == 0:
            return arr_A[:-1] + [min(arr_A[-1], arr_B[0])] + arr_B[1:]

        else:
            return arr_A[:-1] + [max(arr_A[-1], arr_B[0])] + arr_B[1:]

    def candy(self, ratings: [int]) -> int:
        queue_count         = 0
        cur_dir             = -1    # 0 - ascending, 1 - descending
        transition_state    = -1    # -1 - direct concat, 0 - take min of last, 1 - take max of last
        result              = [] 

        for indx in range(len(ratings)):
            cur_num     = ratings[indx]
            next_num    = ratings[indx +1] if indx +1 < len(ratings) else cur_num

            if queue_count == 0:
                if cur_num == next_num:
                    result.append(1)

                elif cur_num < next_num:
                    queue_count += 1
                    cur_dir     = 0

                else:
                    queue_count += 1
                    cur_dir     = 1

            else:
                if cur_num == next_num and cur_dir == 0:
                    queue_count += 1
                    res         = [1 +i for i in range(queue_count)]
                    result      = self.merge(arr_A = result, arr_B = res, transition_state = transition_state)
                    queue_count = 0
                    transition_state = -1

                elif cur_num == next_num and cur_dir == 1:
                    queue_count += 1
                    res         = [queue_count -i for i in range(queue_count)] 
                    result      = self.merge(arr_A = result, arr_B = res, transition_state = transition_state)
                    queue_count = 0
                    transition_state = -1

                elif cur_num < next_num and cur_dir == 0:
                    queue_count += 1 

                elif cur_num < next_num and cur_dir == 1:
                    # Local min
                    queue_count += 1
                    res         = [queue_count -i for i in range(queue_count)] 
                    result      = self.merge(arr_A = result, arr_B = res, transition_state = transition_state)
                    queue_count = 1
                    cur_dir     = 0
                    transition_state = 0

                elif cur_num > next_num and cur_dir == 0:
                    # Local max
                    queue_count += 1
                    res         = [1 +i for i in range(queue_count)] 
                    result      = self.merge(arr_A = result, arr_B = res, transition_state = transition_state)
                    queue_count = 1
                    cur_dir     = 1
                    transition_state = 1

                elif cur_num > next_num and cur_dir == 1:
                    queue_count += 1

        return sum(result)

if __name__ == "__main__":
    sol     = Solution()
    ratings = [1,5,100,9,8,7,6,5,4,3,2,2,9]
    res     = sol.candy(ratings = ratings)

    print(res)