package com.example.demo.controller;

import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin // フロントエンドからのアクセスを許可
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // ① 予約登録API (POST /api/reservations)
    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        try {
            if (reservation.getName() == null || reservation.getDate() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("入力不備があります。");
            }

            Reservation saved = reservationService.saveReservation(reservation);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("登録に失敗しました。");
        }
    }

    // ② 予約一覧・検索API (GET /api/reservations)
    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(@RequestParam(required = false) String email) {
        List<Reservation> list = reservationService.getReservations(email);
        return ResponseEntity.ok(list);
    }

    // ③ 予約キャンセルAPI（削除ではなくステータスを「キャンセル」に更新） (PUT /api/reservations/{id}/cancel)
    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id) {
        Optional<Reservation> optionalReservation = reservationService.findById(id);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setStatus("キャンセル");
            reservationService.updateReservation(reservation);
            return ResponseEntity.ok("予約をキャンセルしました。");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("指定された予約が見つかりません。");
        }
    }
    // ④ 予約削除API (DELETE /api/reservations/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        try {
            Optional<Reservation> optionalReservation = reservationService.findById(id);
            if (optionalReservation.isPresent()) {
                reservationService.deleteReservation(id); // または reservationRepository.deleteById(id);
                return ResponseEntity.ok("予約を完全に削除しました。");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("指定された予約が見つかりません。");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("削除に失敗しました。");
        }
    }
}