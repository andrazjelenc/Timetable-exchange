# Timetable-exchange
Algorithm for optimize lab courses exchange using Hungarian method<br />
<br />
<b>Input format:</b><br />
number_of_students<br />
owned_termin number_of_wanted_termins termin1 termin2...<br />
...<br />
<br />
<b>Output format:</b> (only changes are printed)<br />
student_number -> [wanted_termins] new_termin<br />
...<br />
<br />
<br />
<br />

<b>Test input:</b><br />
35<br />
7 2 4 11<br />
8 2 5 6<br />
13 1 12<br />
4 1 7<br />
4 1 9<br />
0 2 11 4<br />
11 1 0<br />
13 2 7 12<br />
10 2 6 7<br />
0 1 1<br />
3 1 0<br />
2 2 9 0<br />
7 1 6<br />
3 1 9<br />
7 1 4<br />
13 2 7 8<br />
13 2 7 8<br />
13 1 2<br />
13 1 5<br />
13 1 5<br />
13 3 12 5 6<br />
10 1 3<br />
3 1 2<br />
3 2 2 9<br />
10 3 6 7 8<br />
6 1 10<br />
8 2 5 6<br />
10 2 4 9<br />
0 2 4 11<br />
0 1 13<br />
13 1 0<br />
7 1 13<br />
8 2 4 11<br />
10 2 6 4<br />
0 2 6 4<br />
<br />
<b>Test output:</b><br />
3 -> [7] 7<br />
5 -> [4, 11] 11<br />
6 -> [0] 0<br />
11 -> [0, 9] 0<br />
15 -> [7, 8] 8<br />
21 -> [3] 3<br />
22 -> [2] 2<br />
25 -> [10] 10<br />
29 -> [13] 13<br />
30 -> [0] 0<br />
31 -> [13] 13<br />
32 -> [4, 11] 4<br />
34 -> [4, 6] 6<br />
Success: 13<br />
