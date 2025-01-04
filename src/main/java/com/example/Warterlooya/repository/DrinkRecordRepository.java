package com.example.Warterlooya.repository;

import com.example.Warterlooya.domain.DrinkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface DrinkRecordRepository extends JpaRepository<DrinkRecord, Long> {

    @Query("select " +
            "sum(case when hour(r.time) = 8 then r.amount else 0 end) as amount8, " +
            "sum(case when hour(r.time) = 12 then r.amount else 0 end) as amount12, " +
            "sum(case when hour(r.time) = 16 then r.amount else 0 end) as amount16, " +
            "sum(case when hour(r.time) = 20 then r.amount else 0 end) as amount20 " +
            "from DrinkRecord r " +
            "where r.users.id = :userId " +
            "and date(r.time) = :day")
    List<Object[]> findAmountsByTimeOfDay(@Param("userId") Long userId, @Param("day") LocalDate day);


    @Query("select " +
            "date(r.time) as recordDate, " +
            "sum(r.amount) as totalAmount " +
            "from DrinkRecord r " +
            "where r.users.id = :userId " +
            "and date(r.time) between :weekStartDate and :weekEndDate " +
            "group by date(r.time) " +
            "order by date(r.time) asc")
    List<Object[]> findWeeklyTotalAmounts(
            @Param("userId") Long userId,
            @Param("weekStartDate") LocalDate weekStartDate,
            @Param("weekEndDate") LocalDate weekEndDate);
}
