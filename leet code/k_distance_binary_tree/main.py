class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    ans         = []
    explored    = []

    def __init__(self):
        self.ans = []
        self.explored = []
        return

    def exploreDown(self, root: TreeNode, k: int):
        if k < 0 or root == None:
            return

        elif k == 0:
            self.ans.append(root.val)
            return

        self.exploreDown(root = root.left, k = k -1)
        self.exploreDown(root = root.right, k = k -1)
        return

    def _distanceK(self, root: TreeNode, target: TreeNode, k: int) -> [int]:
        if root is None:
            return []

        self.explored.append(root)

        if target.val == root.val:
            # Explore down and up the tree
            
            _explored = self.explored[::-1][: k+1]
            print(list(map(lambda x: x.val, _explored)))

            for indx, each_explored in enumerate(_explored):
                if indx == k and each_explored is not None:
                    self.ans.append(each_explored.val)

                elif indx >= 1 and _explored[indx].left is not None and _explored[indx].left.val == _explored[indx -1].val:
                    self.exploreDown(root = each_explored.right, k = k -indx-1)

                elif indx >= 1 and _explored[indx].right is not None and _explored[indx].right.val == _explored[indx -1].val:
                    self.exploreDown(root = each_explored.left, k = k -indx-1)

                else:
                    self.exploreDown(root = each_explored, k = k)
            
            return self.ans

        res1 = self._distanceK(root = root.left, target = target, k = k)
        res2 = self._distanceK(root = root.right, target = target, k = k)
        self.explored.pop()
        return res1 + res2

    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> [int]:
        resp = self._distanceK(root = root, target = target, k = k)
        self.ans = []
        self.explored = []
        return resp

if __name__ == "__main__":
    # root = [3,5,1,6,2,0,8,None,None,7,4]
    # tree = list(map(lambda each: TreeNode(x = each), root))

    # for indx, each_tree in enumerate(tree):
    #     if each_tree is not None:
    #         each_tree.left = tree[indx * 2 + 1] if indx * 2 + 1 < len(tree) else None
    #         each_tree.right = tree[indx * 2 + 2] if indx * 2 + 2 < len(tree) else None
    # ans = sol.distanceK(root = tree[0], target = TreeNode(x = 1), k = 1)

    sol = Solution()

    root = [0,None,1,None,2,None,3]
    tree = list(map(lambda each: TreeNode(x = each) if each is not None else None, root))

    for indx, each_tree in enumerate(tree):
        if each_tree is not None:
            each_tree.left = tree[indx * 2 + 1] if indx * 2 + 1 < len(tree) else None
            each_tree.right = tree[indx * 2 + 2] if indx * 2 + 2 < len(tree) else None

    ans = sol.distanceK(root = tree[0], target = TreeNode(x = 1), k = 2)
    print(ans)