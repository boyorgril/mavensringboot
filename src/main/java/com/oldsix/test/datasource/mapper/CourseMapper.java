package com.oldsix.test.datasource.mapper;

import com.oldsix.test.datashow.CourseShow;
import com.oldsix.test.datashow.StudentShow;
import com.oldsix.test.datasource.dto.Course;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface CourseMapper {

    @Select({
            "select id, name, empid, sex, profession, status, totalScore, ",
            "tutor, searchDirection, teachername, myclass, grade from student where id ",
            "in (select studentid from student_course_reflect where courseid = #{courseId,jdbcType=BIGINT})"
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
    List<StudentShow> selectStudentByCourseId(Long courseId);

    @Insert({
            "insert into course (coursenumber, coursename, teacher, introduction, score, createtime, updatetime)",
            "values (",
            "#{courseNumber,jdbcType=VARCHAR}, #{courseName,jdbcType=VARCHAR}, #{teacher,jdbcType=VARCHAR}, ",
            "#{introduction,jdbcType=VARCHAR}, #{score,jdbcType=DOUBLE}, #{createTime,jdbcType=DATE}, ",
            "#{updateTime,jdbcType=DATE}",
            ")"
    })
    void insertCourse(Course course);

    @Select({
            "select id, coursenumber, coursename, teacher, introduction, score, numberaccout, maxnumberaccout from course",
            " where 1 = 1"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="coursenumber", property="courseNumber", jdbcType= JdbcType.VARCHAR),
            @Result(column="coursename", property="courseName", jdbcType= JdbcType.VARCHAR),
            @Result(column="teacher", property="teacher", jdbcType= JdbcType.VARCHAR),
            @Result(column="introduction", property="introduction", jdbcType= JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType= JdbcType.DOUBLE),
            @Result(column="numberaccout", property="numberOfStudent", jdbcType= JdbcType.INTEGER),
            @Result(column="maxnumberaccout", property="maxNumberOfStudent", jdbcType= JdbcType.INTEGER),
    })
    List<CourseShow> selectAllCourseNoCondition();

    @Select({
            "select id, coursenumber, coursename, teacher, introduction, score, numberaccout, maxnumberaccout from course",
            "where id = #{courseID,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="coursenumber", property="courseNumber", jdbcType= JdbcType.VARCHAR),
            @Result(column="coursename", property="courseName", jdbcType= JdbcType.VARCHAR),
            @Result(column="teacher", property="teacher", jdbcType= JdbcType.VARCHAR),
            @Result(column="introduction", property="introduction", jdbcType= JdbcType.VARCHAR),
            @Result(column="score", property="score", jdbcType= JdbcType.DOUBLE),
            @Result(column="numberaccout", property="maxNumberOfStudent", jdbcType= JdbcType.INTEGER),
            @Result(column="maxnumberaccout", property="numberOfStudent", jdbcType= JdbcType.INTEGER),
    })
    CourseShow selectCourseByPrimaryKey(long courseID);

    @Update({
            "update course",
            "set coursenumber = #{courseNumber,jdbcType=VARCHAR},",
            "coursename = #{courseName,jdbcType=VARCHAR},",
            "teacher = #{teacher,jdbcType=VARCHAR},",
            "introduction = #{introduction,jdbcType=VARCHAR},",
            "score = #{score,jdbcType=DOUBLE}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    void updateCourseById(Course course);
}
