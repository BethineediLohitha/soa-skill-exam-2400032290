package com.klef.soa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.klef.soa.entity.GSTRecord;
import com.klef.soa.repository.GSTRecordRepository;

@ExtendWith(MockitoExtension.class)
class GSTRecordServiceImplTest {

    @Mock
    private GSTRecordRepository repo;

    @InjectMocks
    private GSTRecordServiceImpl service;

    private GSTRecord record;

    @BeforeEach
    void setUp() {
        record = new GSTRecord();
        record.setGstRecordId(1L);
        record.setName("ABC Traders");
        record.setType("Regular");
        record.setStatus(true);
        record.setLocation("Guntur");
    }

    @Test
    void addGSTRecord_returnsSavedRecord() {
        when(repo.save(record)).thenReturn(record);

        GSTRecord result = service.addGSTRecord(record);

        assertSame(record, result);
        verify(repo).save(record);
    }

    @Test
    void displayAllGSTRecords_returnsAllRecords() {
        when(repo.findAll()).thenReturn(List.of(record));

        List<GSTRecord> result = service.displayAllGSTRecords();

        assertEquals(1, result.size());
        assertEquals("ABC Traders", result.get(0).getName());
    }

    @Test
    void updateGSTRecord_whenRecordExists() {
        GSTRecord updates = new GSTRecord();
        updates.setGstRecordId(1L);
        updates.setName("Updated Traders");
        updates.setType("Composition");
        updates.setStatus(false);
        updates.setLocation("Vijayawada");

        when(repo.findById(1L)).thenReturn(Optional.of(record));
        when(repo.save(record)).thenReturn(record);

        GSTRecord result = service.updateGSTRecord(updates);

        assertNotNull(result);
        assertEquals("Updated Traders", result.getName());
        assertEquals("Composition", result.getType());
        assertFalse(result.isStatus());
        assertEquals("Vijayawada", result.getLocation());
    }

    @Test
    void updateGSTRecord_whenRecordDoesNotExist() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        GSTRecord updates = new GSTRecord();
        updates.setGstRecordId(99L);

        GSTRecord result = service.updateGSTRecord(updates);

        assertNull(result);
    }

    @Test
    void displayGSTRecordById_whenFound() {
        when(repo.findById(1L)).thenReturn(Optional.of(record));

        GSTRecord result = service.displayGSTRecordById(1L);

        assertNotNull(result);
        assertEquals("ABC Traders", result.getName());
    }

    @Test
    void displayGSTRecordById_whenNotFound() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        GSTRecord result = service.displayGSTRecordById(99L);

        assertNull(result);
    }

    @Test
    void deleteGSTRecordById_whenRecordExists() {
        when(repo.existsById(1L)).thenReturn(true);

        String result = service.deleteGSTRecordById(1L);

        assertEquals("GST Record Deleted Successfully", result);
        verify(repo).deleteById(1L);
    }

    @Test
    void deleteGSTRecordById_whenRecordDoesNotExist() {
        when(repo.existsById(99L)).thenReturn(false);

        String result = service.deleteGSTRecordById(99L);

        assertEquals("GST Record ID Not Found", result);
        verify(repo, never()).deleteById(anyLong());
    }

    @Test
    void displayGSTRecordsByName() {
        when(repo.findByName("ABC Traders")).thenReturn(List.of(record));

        List<GSTRecord> result =
                service.displayGSTRecordsByName("ABC Traders");

        assertEquals(1, result.size());
        assertEquals("ABC Traders", result.get(0).getName());
    }

    @Test
    void displayGSTRecordsByType() {
        when(repo.findByType("Regular")).thenReturn(List.of(record));

        List<GSTRecord> result =
                service.displayGSTRecordsByType("Regular");

        assertEquals(1, result.size());
        assertEquals("Regular", result.get(0).getType());
    }

    @Test
    void displayGSTRecordCount() {
        when(repo.count()).thenReturn(5L);

        Long result = service.displayGSTRecordCount();

        assertEquals(5L, result);
    }
}