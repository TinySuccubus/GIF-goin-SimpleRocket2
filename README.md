# GIF-goin-SimpleRocket2
This is a simple JAVA program that converts bmp picture into a special format for Vizzy can use.
It will merge Vertically first.Then scale color information(0\~255) into 0.0000\~0.9945 and put them in a row like "r.rrrr,g.gggg,b.bbbbr.rrrr,g.gggg,b.bbbb".It starts from Left-Top(x0y0) and move like "x0y0x1y0x2y0".
#### use "java -jar \<jarname\>.jar" to run
### This program has 2 functions.
  1. Merge and Translate.
  2. Just Translate.
### To use the 1st function,just make sure picture's name:
  Start with "IMG"
  5 digits of numbers
  End with ".bmp"
The numbers of different picture should be continuous(like IMG00000.bmp IMG000001.bmp).
Then input the numbers of the picture's name which u want to start from at the first time the console ask for input.
Input how many other pictures u want to merge at the 3rd time ask for input.
Then just wait for it is done.The data is in "out.txt".
  
### For the 2nd function
input picture's name(not include ".bmp") at the 1st time the console ask for input.
input any number at the 2nd time ask for input.
input -1 at the 3rd time ask for input.
The rests r the same as the 1st function.

### Make sure files and folders form like this
│   \<jarname\>.jar
  
│   out.txt

└─ img

    └─out
