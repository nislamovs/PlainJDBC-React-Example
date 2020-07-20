package com.example.jdbcexample.services;

import com.example.jdbcexample.mappers.TeacherMapper;
import com.example.jdbcexample.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
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
