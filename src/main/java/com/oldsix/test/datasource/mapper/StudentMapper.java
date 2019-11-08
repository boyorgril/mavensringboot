package com.oldsix.test.datasource.mapper;

import com.oldsix.test.datashow.CourseShow;
import com.oldsix.test.datashow.StudentShow;
import com.oldsix.test.datasource.dto.Course;
import com.oldsix.test.datasource.dto.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


public interface StudentMapper {

    @Select({
            "select id from student",
            "where empid = #{empid,jdbcType=BIGINT}"
    })
    int selectByEmpId(String empid);

    @Select({
            "select count(*) from student",
            "where empid = #{empid,jdbcType=BIGINT}"
    })
    int selectUserByEmpId(String empid);

    @Select({
            "select id, coursenumber, coursename, teacher, introduction, " ,
                    "score, numberaccout, maxnumberaccout from course where" ,
                    " id in (select courseid from student_course_reflect where studentid = #{studentId,jdbcType=BIGINT})"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="courseNumber", property="courseNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="courseName", property="courseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="teacher", property="teacher", jdbcType=JdbcType.VARCHAR),
            @Result(column="introduction", property="introduction", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.DOUBLE),
            @Result(column="numberaccout", property="numberOfStudent", jdbcType=JdbcType.INTEGER),
            @Result(column="maxnumberaccout", property="maxNumberOfStudent", jdbcType=JdbcType.INTEGER),

    })
    List<CourseShow> selectCourseByStudentId(Integer studentId);

    @Insert({
            "insert into student ( name, empid, sex, birthday, profession, createTime, status, ",
            "totalScore, tutor, searchDirection, teachername, myclass, grade)",
            "values( #{name,jdbcType=VARCHAR}, #{empId,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR},",
            "#{birthday,jdbcType=DATE}, #{profession,jdbcType=VARCHAR}, #{createTime,jdbcType=DATE},",
            "#{status,jdbcType=VARCHAR}, #{totalScore,jdbcType=DOUBLE}, #{tutor,jdbcType=VARCHAR},",
            "#{searchDirection,jdbcType=VARCHAR}, #{teacherName,jdbcType=VARCHAR}, #{myclass,jdbcType=VARCHAR},",
            "#{grade,jdbcType=VARCHAR})",
    })
    void saveStudentInfo(Student student);

    @Select({
            "select id, name, empid, sex, profession, status, totalScore, ",
            "tutor, searchDirection, teachername, myclass, grade from student"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="empid", property="empId", jdbcType= JdbcType.VARCHAR),
            @Result(column="sex", property="sex", jdbcType= JdbcType.VARCHAR),
            @Result(column="profession", property="profession", jdbcType= JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column="totalScore", property="totalScore", jdbcType= JdbcType.DOUBLE),
            @Result(column="tutor", property="tutor", jdbcType= JdbcType.VARCHAR),
            @Result(column="searchDirection", property="searchDirection", jdbcType= JdbcType.VARCHAR),
            @Result(column="teachername", property="teacherName", jdbcType= JdbcType.VARCHAR),
            @Result(column="myclass", property="myclass", jdbcType= JdbcType.VARCHAR),
            @Result(column="grade", property="grade", jdbcType= JdbcType.VARCHAR)
    })
    List<StudentShow> selectStudentsNoCondition();

    @Select({
            "select totalScore, status from student",
            "where empid = #{empid,jdbcType=BIGINT}"
    })
    Student selectStudentByEmpId(String empId);

    @Insert({
            "insert into student_course_reflect (studentid, courseid) values",
            "(#{userId,jdbcType=BIGINT}, #{courseid,jdbcType=BIGINT})"
    })
    void insertStudentCourseMap(Integer courseid, Integer userId);

    @Update({
            "UPDATE course set numberaccout = numberaccout + 1 ",
            "where id = #{courseid,jdbcType=BIGINT}"
    })
    void updateCourseNumberAccout(int courseid);

    @Select({
            "select id, coursenumber, coursename, teacher, introduction, " ,
            "score, numberaccout, maxnumberaccout from course where" ,
            " numberaccout != 50 and ",
            " id not in (select courseid from student_course_reflect where studentid = #{studentId,jdbcType=BIGINT})"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="courseNumber", property="courseNumber", jdbcType=JdbcType.VARCHAR),
            @Result(column="courseName", property="courseName", jdbcType=JdbcType.VARCHAR),
            @Result(column="teacher", property="teacher", jdbcType=JdbcType.VARCHAR),
            @Result(column="introduction", property="introduction", jdbcType=JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType=JdbcType.DOUBLE),
            @Result(column="numberaccout", property="numberOfStudent", jdbcType=JdbcType.INTEGER),
            @Result(column="maxnumberaccout", property="maxNumberOfStudent", jdbcType=JdbcType.INTEGER),

    })
    List<CourseShow> selectAllCourseNoSelected(int userId);

    @Update({
            "UPDATE student set totalScore = totalScore + (SELECT score from course where id = #{courseId,jdbcType=BIGINT}) ",
            "WHERE id = #{userId,jdbcType=BIGINT}"
    })
    void updateStudentTotalScore(int courseId, Integer userId);

    @Update({
            "UPDATE student set totalScore = totalScore - (SELECT score from course where id = #{courseId,jdbcType=BIGINT}) ",
            "WHERE id = #{userId,jdbcType=BIGINT}"
    })
    void subStudentTotalScore(int courseId, Integer userId);

    @Update({
            "UPDATE course set numberaccout = numberaccout - 1 ",
            "where id = #{courseid,jdbcType=BIGINT}"
    })
    void subCourseStudentNumber(int courseid);

    @Delete({
            "DELETE from student_course_reflect where ",
            "courseid = #{courseId,jdbcType=BIGINT} and studentid = #{userId,jdbcType=BIGINT}"
    })
    void subReflectWithStudentAndCourse(int courseId, Integer userId);

    @Update({
            "Update student set totalScore = totalScore + #{scoreDifferents,jdbcType=DOUBLE} where id in",
            "(select studentid from student_course_reflect where courseid  = #{courseId,jdbcType=BIGINT})"
    })
    void updateScoreOfCourseChange(double scoreDifferents, Long courseId);

    @Update({
            "Update student set ",
            "name = #{name,jdbcType=VARCHAR}, empid = #{empId,jdbcType=VARCHAR},",
            "sex = #{sex,jdbcType=VARCHAR}, profession = #{profession,jdbcType=VARCHAR},",
            "grade = #{grade,jdbcType=VARCHAR}, tutor = #{tutor,jdbcType=VARCHAR},",
            "searchDirection = #{searchDirection,jdbcType=VARCHAR}, teachername = #{teacherName,jdbcType=VARCHAR},",
            "myclass = #{myclass,jdbcType=VARCHAR} where id = #{id,jdbcType=BIGINT}"
    })
    void updateInfoByPrimary(Student student);
}
