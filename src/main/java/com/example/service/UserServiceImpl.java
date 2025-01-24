package com.example.service;

import com.example.error.exception.EntityNotFoundException;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Сервис для управления пользователями.
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Создает нового пользователя.
     *
     * @param user пользователь, которого нужно создать.
     * @return созданный пользователь.
     */
    @Override
    public User create(User user) {
        log.info("Create user {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User resultUser = userRepository.save(user);
        log.info("Created user {}", resultUser);
        return resultUser;
    }

    /**
     * Получает пользователя по его ID.
     *
     * @param id идентификатор пользователя.
     * @return найденный пользователь.
     * @throws EntityNotFoundException если пользователь не найден.
     */
    @Override
    @Transactional(readOnly = true)
    public User getById(long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.warn("User not found with ID {}", id);
            return new EntityNotFoundException("User with ID=" + id + " not found");
        });
    }

    /**
     * Обновляет данные пользователя.
     *
     * @param updateUser пользователь с обновленными данными.
     * @return обновленный пользователь.
     */
    @Override
    public User update(User updateUser) {
        final User saveUser = getById(updateUser.getId());
        Optional.ofNullable(updateUser.getName()).ifPresent(saveUser::setName);
        Optional.ofNullable(updateUser.getEmail()).ifPresent(saveUser::setEmail);
        if (updateUser.getPassword() != null) {
            saveUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        return userRepository.save(saveUser);
    }

    /**
     * Удаляет пользователя по его ID.
     *
     * @param id идентификатор пользователя.
     */
    @Override
    public void delete(long id) {
        log.info("Delete user with ID {}", id);
        userRepository.existsById(id);
        userRepository.deleteById(id);
        log.info("Deleted user with ID {}", id);
    }
}