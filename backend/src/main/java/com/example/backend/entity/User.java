package com.example.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", length = 45)
	private Long id;
	
    @Column(name="username", length = 255)
	private String username;
	
    @Column(name="email", length = 255)
	private String email;
    
    @Column(name="password", length = 255)
	private String password;
	
    private Integer forgotPassword;

}
