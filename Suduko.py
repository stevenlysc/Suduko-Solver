class Suduko(object):
    
    def __init__(self, s):
        self.s = s
    
    def check_all(self, i, j, value):
        return self.check_row(i, j, value) and self.check_column(i, j, value) and self.check_small_sudoku(i, j, value)
    
    def check_row(self, i, j, value):
        return value not in self.s[i]
    
    def check_column(self, i, j, value):
        column = [self.s[v][j] for v in range(9)]
        return value not in column
    
    def check_small_sudoku(self, i, j, value):
        small_sudoku = [self.s[m][n] for m in range(i/3*3,(i/3+1)*3) for n in range(j/3*3,(j/3+1)*3)]
        return value not in small_sudoku
    
    def recursion_search(self):
        i,j = self.start_point()
        if i >=8 and j >=8 and self.s[8][8]:
            return True
        
        for value in range(1,10):
            if self.check_all(i, j, value):
                self.s[i][j] = value
                if not self.recursion_search():
                    self.s[i][j] = 0
                else:
                    return True
        return False 
    
    def start_point(self):
        for i in range(9):
            for j in range(9):
                if not self.s[i][j]:
                    return i,j
        return i,j

if '__main__' == __name__:
    
    s = [[6,0,3,7,0,0,0,0,0],
         [0,1,0,0,3,0,0,0,4],
         [0,8,0,9,5,6,0,0,3],
         [4,0,2,3,8,0,0,0,1],
         [1,0,0,2,0,5,0,0,0],
         [0,0,7,4,6,0,0,5,0],
         [2,9,1,0,0,0,7,8,0],
         [8,4,0,0,0,0,2,0,6],
         [0,7,0,5,2,0,0,1,9]]

    S = Suduko(s)
         
    S.recursion_search()
         
    for i in range(9):
        print S.s[i]