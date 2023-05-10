package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	@JsonIgnore
	@NotNull
	@Column(name = "password_hash", nullable = false)
	private String password;
	@Email
	@Column(name = "email", unique = true)
	private String email;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "accountauthority",
			joinColumns = @JoinColumn(name = "account_id"),
			inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private Set<Authority> authorities = new HashSet<Authority>();
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
	@JsonIgnore
	private Profile profile;
	@ManyToOne
	@JoinColumn(name = "rank_id")
	private Rank rank;
}
