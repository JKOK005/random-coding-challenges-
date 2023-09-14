# class Solution:
#     def appealSum(self, s: str) -> int:
#         mat = [ [ set() for _ in range(len(s)) ] for _ in range(len(s)) ]

#         for col in range(len(s), -1, -1):
#             for row in range(col, len(s)):
#                 mat[row][col] = mat[row][col +1] | set(s[col]) if col +1 < len(s) else set(s[col])

#         total_count = 0
#         for col in range(len(s), -1, -1):
#             for row in range(col, len(s)):
#                 total_count += len(mat[row][col])

#         return total_count

class Solution:
    def appealSum(self, s: str) -> int:
        prev_cols   = [ set() for _ in range(len(s)) ]
        cur_cols    = [ set() for _ in range(len(s)) ]
        total_count = 0

        for col in range(len(s), -1, -1):
            for row in range(col, len(s)):
                cur_cols[row]   = prev_cols[row +1] | set(s[col]) if row +1 < len(s) else set(s[col])
                total_count     += len(cur_cols[row])

            prev_cols = cur_cols
        return total_count

if __name__ == "__main__":
    sol     = Solution()
    resp    = sol.appealSum(s = "abbca")
    print(resp)