package com.example.jdbcexample.services;

import com.example.jdbcexample.domain.dao.SubjectMarkDAO;
import com.example.jdbcexample.domain.dto.AbstractDTO;
import com.example.jdbcexample.domain.dto.SubjectMarkDTO;
import com.example.jdbcexample.mappers.MarkMapper;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarksService {

    private final BasicDataSource dataSource;
    private final MarkMapper mapper;

    private final String MARKS_RETRIEVAL_QUERY = "select * FROM marks";
    private final String MARKS_PAGE_RETRIEVAL_QUERY = "select * FROM marks WHERE id BETWEEN ? AND ? order by ? ?";
    private final String MARKS_GET_BY_ID_RETRIEVAL_QUERY = "select * FROM marks WHERE id = ?";
    private final String MARKS_GET_BY_DATE_RETRIEVAL_QUERY = "select * FROM marks WHERE date >= ? AND date <= ?";
    private final String MARKS_NEW_MARK_ADD_QUERY = "INSERT INTO marks(id, subject_id, pupil_id, date, value) VALUES (?, ?, ?, ?, ?)";
    private final String MARKS_EXISTING_MARK_UPDATE_QUERY = "UPDATE INTO marks(id, subject_id, pupil_id, date, value) VALUES (?, ?, ?, ?, ?)";
    private final String MARKS_EXISTING_MARK_DELETE_QUERY = "DELETE FROM marks where id = ?";

    @SneakyThrows
    public List<SubjectMarkDTO> getAllMarks() {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_RETRIEVAL_QUERY);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<SubjectMarkDTO> retrieveMarksPage(String pagenum, String pagesize, String sort, String group) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_PAGE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum));
        stmt.setInt(i++, parseInt(pagesize) * parseInt(pagenum) + parseInt(pagesize));
        stmt.setString(i++, group);
        stmt.setString(i++, sort);

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<SubjectMarkDTO> retrieveMarksByPupilId(String pupilId) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_GET_BY_ID_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setInt(i++, parseInt(pupilId));

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public List<SubjectMarkDTO> getMarksByDateInterval(String startDate, String endDate) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_GET_BY_DATE_RETRIEVAL_QUERY);

        int i = 1;
        stmt.setDate(i++, Date.valueOf(startDate));
        stmt.setDate(i++, Date.valueOf(endDate));

        System.out.println(">>>   "+stmt.toString());

        return executeQuery(stmt).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    public AbstractDTO addNewMark(SubjectMarkDTO mark) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_NEW_MARK_ADD_QUERY);

        int i = 1;
        stmt.setRowId(i++, null);
        stmt.setLong(i++, parseLong(mark.getSubject_id()));
        stmt.setLong(i++, parseLong(mark.getPupil_id()));
        stmt.setDate(i++, Date.valueOf(mark.getDate()));
        stmt.setInt(i++, mark.getValue());

        System.out.println(">>>   "+stmt.toString());

        Long markId = executeInsert(stmt);

        return AbstractDTO.builder().id(valueOf(markId)).dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO editMark(SubjectMarkDTO mark) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_EXISTING_MARK_UPDATE_QUERY);

        int i = 1;
        stmt.setLong(i++, parseLong(mark.getId()));
        stmt.setLong(i++, parseLong(mark.getSubject_id()));
        stmt.setLong(i++, parseLong(mark.getPupil_id()));
        stmt.setDate(i++, Date.valueOf(mark.getDate()));
        stmt.setInt(i++, mark.getValue());

        System.out.println(">>>   "+stmt.toString());

        Long markId = executeUpdate(stmt);

        return AbstractDTO.builder().id(valueOf(markId)).dateTime(LocalDateTime.now()).build();
    }

    @SneakyThrows
    public AbstractDTO deleteMark(String markId) {

        @Cleanup Connection conn = getConnection();
        @Cleanup PreparedStatement stmt = conn.prepareStatement(MARKS_EXISTING_MARK_DELETE_QUERY);

        int i = 1;
        stmt.setLong(i++, parseLong(markId));

        System.out.println(">>>   "+stmt.toString());

        return AbstractDTO.builder().dateTime(LocalDateTime.now()).build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private List<SubjectMarkDAO> executeQuery(PreparedStatement stmt) {
        List<SubjectMarkDAO> marks = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SubjectMarkDAO mark = SubjectMarkDAO.builder()
                        .id(rs.getLong("id"))
                        .subjectId(rs.getLong("subject_id"))
                        .pupilId(rs.getLong("pupil_id"))
                        .date(rs.getDate("date"))
                        .value(rs.getInt("id"))
                        .build();

                marks.add(mark);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return marks;
    }

    private Long executeUpdate(PreparedStatement stmt) {
        return executeInsert(stmt);
    }

    private Long executeInsert(PreparedStatement stmt) {

        long retVal = 0L;

        try {
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating new mark failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    retVal = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating new mark failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retVal;
    }
}
