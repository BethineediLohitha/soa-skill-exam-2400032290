package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.GSTRecord;
import com.klef.soa.repository.GSTRecordRepository;

@Service
public class GSTRecordServiceImpl implements GSTRecordService
{
	@Autowired
	private GSTRecordRepository repo;

	@Override
	public GSTRecord addGSTRecord(GSTRecord gstRecord)
	{
		return repo.save(gstRecord);
	}

	@Override
	public List<GSTRecord> displayAllGSTRecords()
	{
		return repo.findAll();
	}

	@Override
	public GSTRecord updateGSTRecord(GSTRecord g)
	{
		Optional<GSTRecord> optional = repo.findById(g.getGstRecordId());

		if(optional.isPresent())
		{
			GSTRecord gstRecord = optional.get();
			gstRecord.setName(g.getName());
			gstRecord.setType(g.getType());
			gstRecord.setStatus(g.isStatus());
			gstRecord.setLocation(g.getLocation());

			return repo.save(gstRecord);
		}
		else
		{
			return null;
		}
	}

	@Override
	public GSTRecord displayGSTRecordById(Long id)
	{
		return repo.findById(id).orElse(null);
	}

	@Override
	public String deleteGSTRecordById(Long id)
	{
		boolean status = repo.existsById(id);

		if(status)
		{
			repo.deleteById(id);
			return "GST Record Deleted Successfully";
		}
		else
		{
			return "GST Record ID Not Found";
		}
	}

	@Override
	public List<GSTRecord> displayGSTRecordsByName(String name)
	{
		return repo.findByName(name);
	}

	@Override
	public List<GSTRecord> displayGSTRecordsByType(String type)
	{
		return repo.findByType(type);
	}

	@Override
	public Long displayGSTRecordCount()
	{
		return repo.count();
	}
}