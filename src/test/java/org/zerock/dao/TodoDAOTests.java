package org.zerock.dao;

import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVo;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {

    private TodoDAO todoDAO;

    @BeforeEach
    public void ready() {
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception {
        System.out.println(todoDAO.getTime());
    }
@Test
    public void testTime2() throws Exception {
        System.out.println(todoDAO.getTime2());
    }

    @Test
    public void testInsert() throws Exception {
        TodoVo todoVo = TodoVo.builder().title("Sample Title...").dueDate(LocalDate.of(2021,12,31)).build();
        todoDAO.insert(todoVo);
    }


    @Test
    public void testList() throws Exception {
        List<TodoVo> list = todoDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void testSelectOne() throws Exception {
     Long tno = 1L; // 반드시 존재하는 번호를 이용
        TodoVo vo = todoDAO.selectOne(tno);
        System.out.println(vo);
    }


    @Test
    public void testupdateOne() throws Exception {

        TodoVo vo = TodoVo.builder().tno(1L).title("Sample Title...").dueDate(LocalDate.of(2021,12,31)).finished(true).build();

        todoDAO.updateOne(vo);
    }

}
