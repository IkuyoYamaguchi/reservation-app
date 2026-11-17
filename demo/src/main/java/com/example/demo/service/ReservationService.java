package com.example.demo.service;

import com.example.demo.entity.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // 予約の保存・登録処理
    public Reservation saveReservation(Reservation reservation) {
        if (reservation.getStatus() == null || reservation.getStatus().isEmpty()) {
            reservation.setStatus("予約確定");
        }
        return reservationRepository.save(reservation);
    }

    // 予約の一覧取得またはメールアドレスでの絞り込み検索
    public List<Reservation> getReservations(String email) {
        if (email != null && !email.isEmpty()) {
            return reservationRepository.findAll()
                    .stream()
                    .filter(r -> email.equals(r.getEmail()))
                    .collect(Collectors.toList());
        } else {
            return reservationRepository.findAll();
        }
    }

    // IDによる予約の取得
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    // キャンセル処理などのための保存
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    // 予約を削除する
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}