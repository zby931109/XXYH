select A.*, B.C_SCORE '1课程分数', C.C_SCORE '2课程分数'
from T_STUDENT as A, T_SCORE as B, T_SCORE as C
where
A.C_STUDENT_NUM = B.C_STUDENT_NUM
and
B.C_COURSE_NUM = 1
and
A.C_STUDENT_NUM = C.C_STUDENT_NUM
and
C.C_COURSE_NUM = 2
and
B.C_SCORE > C.C_SCORE;


select A.*
from T_STUDENT as A
left join T_SCORE as B
on
A.C_STUDENT_NUM = B.C_STUDENT_NUM
and
B.C_COURSE_NUM = 1
left join T_SCORE as C
on
A.C_STUDENT_NUM = C.C_STUDENT_NUM
and
C.C_COURSE_NUM = 2
where
B.C_SCORE > ifnull(C.C_SCORE , 0);




select A.*
from T_STUDENT as A,T_COURSE as B, T_TEACHER as C, T_SCORE as D
where
C.C_TEACHER_NUM = B.C_TEACHER_NUM
and
B.C_COURSE_NUM = D.C_COURSE_NUM
and
D.C_STUDENT_NUM = A.C_STUDENT_NUM
and
C.C_TEACHER_NAME = '张三';




select A.C_STUDENT_NUM, count(A.C_STUDENT_NUM)
from T_STUDENT as A, T_SCORE as B
where A.C_STUDENT_NUM = B.C_STUDENT_NUM
group by A.C_STUDENT_NUM ;



select B.*, count(A.C_STUDENT_NUM) count
from T_SCORE as A, T_STUDENT as B
where A.C_STUDENT_NUM = B.C_STUDENT_NUM
group by A.C_STUDENT_NUM
having
count < (
select count(T_COURSE.C_COURSE_NUM)
from
T_COURSE
);




//查出叫 孙风 的 学生
select * from T_STUDENT
where
C_NAME = '孙风';

//查出所有姓孙的学生
select * from T_STUDENT
where
C_NAME like '孙%';

//两个字符的
select * from T_STUDENT
where
C_NAME like '孙_';

//至少两个字符的
select * from T_STUDENT
where
C_NAME like '孙%_';


