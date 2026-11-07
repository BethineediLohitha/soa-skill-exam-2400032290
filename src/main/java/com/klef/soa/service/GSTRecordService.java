package com.klef.soa.service;

import java.util.List;

import com.klef.soa.entity.GSTRecord;

public interface GSTRecordService
{
	GSTRecord addGSTRecord(GSTRecord gstRecord);
	List<GSTRecord> displayAllGSTRecords();
	GSTRecord updateGSTRecord(GSTRecord gstRecord);
	GSTRecord displayGSTRecordById(Long id);
	String deleteGSTRecordById(Long id);
	List<GSTRecord> displayGSTRecordsByName(String name);
	List<GSTRecord> displayGSTRecordsByType(String type);
	Long displayGSTRecordCount();
}