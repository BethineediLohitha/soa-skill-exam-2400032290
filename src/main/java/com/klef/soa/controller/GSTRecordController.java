package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.entity.GSTRecord;
import com.klef.soa.service.GSTRecordService;

@RestController
@RequestMapping("/gstrecord")
public class GSTRecordController
{
	@Autowired
	private GSTRecordService service;

	@GetMapping("/")
	public String test()
	{
		return "SOA Programming & MicroServices";
	}

	@PostMapping("/add")
	public ResponseEntity<GSTRecord> addGSTRecord(@RequestBody GSTRecord g)
	{
		GSTRecord gstRecord = service.addGSTRecord(g);
		return ResponseEntity.status(201).body(gstRecord);
	}

	@GetMapping("/displayall")
	public ResponseEntity<List<GSTRecord>> displayAllGSTRecords()
	{
		List<GSTRecord> records = service.displayAllGSTRecords();
		return ResponseEntity.status(200).body(records);
	}

	@GetMapping("/display")
	public ResponseEntity<?> displayGSTRecordById(@RequestParam Long id)
	{
		GSTRecord record = service.displayGSTRecordById(id);

		if(record != null)
		{
			return ResponseEntity.status(200).body(record);
		}
		else
		{
			return ResponseEntity.status(404).body("GST Record ID Not Found");
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateGSTRecord(@RequestBody GSTRecord record)
	{
		GSTRecord g = service.updateGSTRecord(record);

		if(g != null)
		{
			return ResponseEntity.ok(g);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("GST Record ID Not Found");
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteGSTRecordById(@PathVariable Long id)
	{
		String message = service.deleteGSTRecordById(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/displaybyname/{name}")
	public ResponseEntity<List<GSTRecord>> displayGSTRecordsByName(
			@PathVariable String name)
	{
		List<GSTRecord> records = service.displayGSTRecordsByName(name);
		return ResponseEntity.status(200).body(records);
	}

	@GetMapping("/displaybytype/{type}")
	public ResponseEntity<List<GSTRecord>> displayGSTRecordsByType(
			@PathVariable String type)
	{
		List<GSTRecord> records = service.displayGSTRecordsByType(type);
		return ResponseEntity.status(200).body(records);
	}

	@GetMapping("/count")
	public ResponseEntity<String> displayGSTRecordCount()
	{
		Long count = service.displayGSTRecordCount();
		String msg = "Total GST Records=" + count;
		return ResponseEntity.ok(msg);
	}
}