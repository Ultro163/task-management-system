package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.security.PersonDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Кастомный сервис для загрузки деталей пользователя по email.
 * Реализация интерфейса {@link UserDetailsService}.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Загружает детали пользователя по его email.
     *
     * @param email email пользователя.
     * @return объект {@link UserDetails}, представляющий пользователя.
     * @throws UsernameNotFoundException если пользователь с указанным email не найден.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new PersonDetails(user);
    }
}
