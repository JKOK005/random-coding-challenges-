class Solution:
    def maxProfit(self, k: int, prices: [int]) -> int:
        sells = k
        table = [[[0 for _ in range(2)] for _ in range(sells)] for _ in range(len(prices))]

        for indx in range(len(prices) -1, -1, -1):
            for sell in range(sells -1, -1, -1):
                for opt in [0, 1]:
                    # Allowed to buy or pass 
                    table[indx][sell][0] = max(
                                                -1 * prices[indx] + (table[indx +1][sell][1] if indx +1 < len(prices) else 0),
                                                table[indx +1][sell][0] if indx +1 < len(prices) else 0
                                            )

                    # Allowed to sell or pass
                    table[indx][sell][1] = max(
                                                (prices[indx] + (table[indx +1][sell -1][0] if indx +1 < len(prices) else 0)) if sell -1 >= 0 else prices[indx],
                                                table[indx +1][sell][1] if indx +1 < len(prices) else 0
                                            )
        
        return table[0][-1][0]

if __name__ == "__main__":
    sol = Solution()
    prices = [3,2,6,5,0,3]
    k = 2
    res = sol.maxProfit(k = k, prices = prices)
    print(res)