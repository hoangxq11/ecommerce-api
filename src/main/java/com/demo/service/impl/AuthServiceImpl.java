package com.demo.service.impl;

import com.demo.model.Account;
import com.demo.model.Authority;
import com.demo.repository.AccountRepository;
import com.demo.repository.AuthorityRepository;
import com.demo.service.AuthService;
import com.demo.web.dto.request.LoginRequest;
import com.demo.web.dto.request.SignupRequest;
import com.demo.web.dto.response.JwtResponse;
import com.demo.web.exception.AuthenticateException;
import com.demo.web.exception.EntityNotFoundException;
import com.demo.web.exception.UsernameOrEmailTakenException;
import com.demo.web.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse authenticateAccount(LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtils.generateJwtToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<String> authorities = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return new JwtResponse(jwtToken, "Bearer", userDetails.getUsername(), authorities);

        } catch (AuthenticationException authenticationException){
            throw new AuthenticateException();
        }

    }

    @Override
    public void registerAccount(SignupRequest signupRequest) {
        if(accountRepository.existsByUsernameOrEmail(signupRequest.getUsername(), signupRequest.getEmail()))
            throw new UsernameOrEmailTakenException();

        Account account =  new Account();
        account.setUsername(signupRequest.getUsername());
        account.setEmail(signupRequest.getEmail());
        account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> authoritiesString = signupRequest.getAuthorities();
        Set<Authority> authorities = new HashSet<>();

        if (authoritiesString == null){
            authorities.add(authorityRepository.findById("ROLE_CUSTOMER").orElseThrow(
                    () -> new EntityNotFoundException(Authority.class.getName(), "ROLE_CUSTOMER")
            ));
        }
        else {
            authoritiesString.forEach(authority -> authorities.add(authorityRepository.findById(authority).orElseThrow(
                    () -> new EntityNotFoundException(Authority.class.getName(), authority)
            )));
        }
        account.setAuthorities(authorities);
        accountRepository.save(account);
    }
}
