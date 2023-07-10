package org.zerock.jdbcex.dao;

import com.mysql.cj.protocol.Resultset;
import lombok.Cleanup;
import lombok.Data;
import org.zerock.jdbcex.domain.TodoVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class TodoDAO {

    public String getTime() {

        String now = null;

        try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("select now()");

             ResultSet resultset = (ResultSet) preparedStatement.executeQuery();
        ) {
            ((ResultSet) resultset).next();
            now = ((ResultSet) resultset).getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception {

        String now = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");

        @Cleanup ResultSet resultset = preparedStatement.executeQuery();

        resultset.next();
        now = resultset.getString(1);

        return now;
    }

    public void insert(TodoVo vo)  throws Exception {
        String sql = "insert into tbl_todo(title, dueDate ,finished) values (? ,?,?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
        preparedStatement.setBoolean(3, vo.isFinished());

        preparedStatement.executeUpdate();

    }


    public List<TodoVo> selectAll() throws Exception {
        String sql = "select * from tbl_todo";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        List<TodoVo> list = new ArrayList<>();

        while (resultSet.next()) {
            TodoVo vo = TodoVo.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(vo);
        }
        return list;
    }

    public TodoVo selectOne(Long tno) throws Exception {
        String sql = "select * from tbl_todo where tno = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,tno);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        List<TodoVo> list = new ArrayList<>();


            TodoVo vo = TodoVo.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();


        return vo;
    }
    public void deleteOne(Long tno) throws Exception {
        String sql = "delete from tbl_todo where tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1,tno);
        preparedStatement.executeUpdate();

    }

    public void updateOne(TodoVo todoVo) throws Exception {
        String sql = "update tbl_todo set title =? , dueDate = ?, finished = ? where tno =?";


        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,todoVo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVo.getDueDate()));
        preparedStatement.setBoolean(3, todoVo.isFinished());
        preparedStatement.setLong(4,todoVo.getTno());
        preparedStatement.executeUpdate();

    }
}
