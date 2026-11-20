package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "お名前は必須です。")
    @Size(max = 20, message = "お名前は20文字以内で入力してください。")
    private String name;

    @NotBlank(message = "メールアドレスは必須です。")
    @Email(message = "正しいメールアドレス形式で入力してください。")
    private String email;

    @NotBlank(message = "電話番号は必須です。")
    @Pattern(regexp = "^[0-9]{10,11}$", message = "電話番号は半角数字10〜11桁で入力してください。")
    private String tel;

    @NotBlank(message = "予約希望日は必須です。")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "予約希望日の形式が正しくありません。")
    private String date;

    private String status;

    // --- Getter と Setter ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}