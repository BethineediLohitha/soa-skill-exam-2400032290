
package com.klef.soa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.klef.soa.entity.GSTRecord;

@DataJpaTest
public class GSTRecordRepositoryTest {

    @Autowired
    private GSTRecordRepository repo;

    private GSTRecord r1;
    private GSTRecord r2;
    private GSTRecord r3;

    @BeforeEach
    void setUp() {

        repo.deleteAll();

        r1 = new GSTRecord();
        r1.setName("ABC Traders");
        r1.setType("Regular");
        r1.setStatus(true);
        r1.setLocation("Vijayawada");

        r2 = new GSTRecord();
        r2.setName("XYZ Enterprises");
        r2.setType("Composition");
        r2.setStatus(true);
        r2.setLocation("Guntur");

        r3 = new GSTRecord();
        r3.setName("ABC Traders");
        r3.setType("Regular");
        r3.setStatus(false);
        r3.setLocation("Tenali");

        repo.saveAll(List.of(r1, r2, r3));
        repo.flush();
    }

    @Test
    void testFindAll() {
        List<GSTRecord> records = repo.findAll();

        assertNotNull(records);
        assertEquals(3, records.size());
    }

    @Test
    void testFindById() {

        Optional<GSTRecord> result =
                repo.findById(r1.getGstRecordId());

        assertTrue(result.isPresent());

        Optional<GSTRecord> missing =
                repo.findById(99999L);

        assertFalse(missing.isPresent());
    }

    @Test
    void testFindByName() {

        List<GSTRecord> records =
                repo.findByName("ABC Traders");

        assertEquals(2, records.size());
    }

    @Test
    void testCount() {
        assertEquals(3L, repo.count());
    }

    @Test
    void testEntityDetailsAndFindByType() {

        List<GSTRecord> records =
                repo.findByType("Regular");

        assertEquals(2, records.size());

        GSTRecord record = records.get(0);

        assertNotNull(record.getGstRecordId());
        assertNotNull(record.getName());
        assertNotNull(record.getType());
        assertNotNull(record.getLocation());
    }
}
