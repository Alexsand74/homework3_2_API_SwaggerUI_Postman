package com.example.homework3_2_api_swaggerui_postman.service;

import com.example.homework3_2_api_swaggerui_postman.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    StudentService studentServis = new StudentService();
    @Test
    void testAdd() {
        var result = studentServis.add(new Student(null, "test", 25));
        assertEquals(result.getId(), 0);
        assertEquals(result.getName(), "test");
        assertEquals(result.getAge(), 25);
    }

    @Test
    void testGet() {
        var Student = studentServis.add(new Student(null, "test", 25));
        var result = studentServis.get(Student.getId());

        assertEquals(result.getName(), "test");
        assertEquals(result.getAge(), 25);
    }
    @Test
    void testUpdate() {
        var result1 = studentServis.update(new Student(99999L, "updated", 25));
        assertNull(result1);

        var addedStudent = studentServis.add(new Student(null, "test", 20));
        addedStudent.setName("updated_name");
        addedStudent.setAge(30);

        var result2 = studentServis.update(addedStudent);
        assertEquals(result2.getName(), "updated_name");
        assertEquals(result2.getAge(), 30);
    }

    @Test
    void testFilterByAge() {
        var added1 = studentServis.add(new Student(null, "test1", 20));
        var added2 = studentServis.add(new Student(null, "test2", 25));
        var added3 = studentServis.add(new Student(null, "test3", 25));

        var result = studentServis.filterByAge(25);
        Assertions.assertThat(result).containsExactly(added2, added3);
    }
    @Test
    void testRemove() {
        var Student = studentServis.add(new Student(null, "test1", 22));
        assertNotNull(studentServis.get(Student.getId()));
        assertTrue(studentServis.remove(Student.getId()));
        assertFalse(studentServis.remove(99999999L));

    }
}