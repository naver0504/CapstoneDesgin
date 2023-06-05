package com.example.capstone.repository;

import com.example.capstone.domain.IotPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IotPlatformRepository extends JpaRepository<IotPlatform, Integer> {

    public IotPlatform findTop1ByOrderByDateTimeDesc();

    public IotPlatform findByDateTimeBetween(Date startTime, Date endTime);



}
