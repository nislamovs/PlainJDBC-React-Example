package com.example.jdbcexample.services;

import com.example.jdbcexample.dao.SubjectMarkDAO;
import com.example.jdbcexample.mappers.TeacherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    private final BasicDataSource dataSource;
    private final TeacherMapper mapper;

    private final String TEACHERS_RETRIEVAL_QUERY = "select * FROM teachers";
    private final String TEACHERS_PAGE_RETRIEVAL_QUERY = "select * FROM teachers WHERE id BETWEEN ? AND ? order by ? ?";

    ////////////////////////////////////////////////////////////////////////////////////////////////

//    private Connection getConnection() throws SQLException {
//        return dataSource.getConnection();
//    }
//
//    private List<SubjectMarkDAO> executeQuery(PreparedStatement stmt) {
//        List<SubjectMarkDAO> marks = new ArrayList<>();
//        try {
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                SubjectMarkDAO mark = SubjectMarkDAO.builder()
//                        .id(rs.getLong("id"))
//                        .subject_id(rs.getLong("subject_id"))
//                        .subject_id(rs.getLong("pupil_id"))
//                        .date(rs.getDate("date"))
//                        .value(rs.getInt("id"))
//                        .build();
//
//                marks.add(mark);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//
//        return marks;
//    }
}
