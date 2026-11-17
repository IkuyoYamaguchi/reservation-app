package com.example.demo.repository;

import com.example.demo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // ← これが絶対に必要です！

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 名前で予約データを検索するメソッド
    List<Reservation> findByName(String name);

}