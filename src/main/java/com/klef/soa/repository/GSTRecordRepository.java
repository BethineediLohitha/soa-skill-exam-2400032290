package com.klef.soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.GSTRecord;
import java.util.List;

@Repository
public interface GSTRecordRepository extends JpaRepository<GSTRecord, Long>
{
	List<GSTRecord> findByName(String name);

	List<GSTRecord> findByType(String type);
}