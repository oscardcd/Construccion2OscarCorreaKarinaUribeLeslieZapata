package com.mycompany.clinicmanagement.adapter.in.mapper;

import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Convierte un CreateUserRequest a entidad User
     */
    public User toEntity(CreateUserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setDocumentNumber(request.documentNumber());
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPhone(request.phone());
        user.setBirthDate(request.birthDate());
        user.setAddress(request.address());
        user.setRole(User.Role.valueOf(request.role()));
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setActive(true);
        user.setCreatedAt(java.time.LocalDateTime.now());
        user.setUpdatedAt(java.time.LocalDateTime.now());

        return user;
    }

    /**
     * Convierte un UpdateUserRequest a entidad User
     */
    public User toEntity(UserController.UpdateUserRequest request, Long id) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setId(id);
        user.setDocumentNumber(request.documentNumber());
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPhone(request.phone());
        user.setBirthDate(request.birthDate());
        user.setAddress(request.address());
        user.setRole(User.Role.valueOf(request.role()));
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setActive(request.active());
        user.setUpdatedAt(java.time.LocalDateTime.now());

        return user;
    }

    /**
     * Convierte una entidad User a CreateUserResponse
     */
    public CreateUserResponse toCreateResponse(User user) {
        if (user == null) {
            return null;
        }

        return new UserController.CreateUserResponse(
                user.getId(),
                "Usuario creado exitosamente");
    }

    /**
     * Convierte una entidad User a UpdateUserResponse
     */
    public UserController.UpdateUserResponse toUpdateResponse(User user) {
        if (user == null) {
            return null;
        }

        return new UserController.UpdateUserResponse(
                user.getId(),
                "Usuario actualizado exitosamente");
    }

    /**
     * Convierte una entidad User a UserDTO
     */
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .documentNumber(user.getDocumentNumber())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .address(user.getAddress())
                .role(user.getRole().name())
                .username(user.getUsername())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    /**
     * Convierte un UserDTO a entidad User
     */
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setDocumentNumber(dto.getDocumentNumber());
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setBirthDate(dto.getBirthDate());
        user.setAddress(dto.getAddress());
        user.setRole(User.Role.valueOf(dto.getRole()));
        user.setUsername(dto.getUsername());
        user.setActive(dto.isActive());
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());

        return user;
    }

    /**
     * Convierte una entidad User a UserSummaryDTO
     */
    public UserSummaryDTO toSummaryDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserSummaryDTO.builder()
                .id(user.getId())
                .documentNumber(user.getDocumentNumber())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .active(user.isActive())
                .build();
    }

    /**
     * Convierte una entidad User a UserProfileDTO
     */
    public UserProfileDTO toProfileDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserProfileDTO.builder()
                .id(user.getId())
                .documentNumber(user.getDocumentNumber())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .address(user.getAddress())
                .role(user.getRole().name())
                .username(user.getUsername())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    /**
     * Actualiza una entidad User con datos de UpdateUserRequest
     */
    public void updateEntity(User existingUser, UserController.UpdateUserRequest request) {
        if (existingUser == null || request == null) {
            return;
        }

        existingUser.setDocumentNumber(request.documentNumber());
        existingUser.setFullName(request.fullName());
        existingUser.setEmail(request.email());
        existingUser.setPhone(request.phone());
        existingUser.setBirthDate(request.birthDate());
        existingUser.setAddress(request.address());
        existingUser.setRole(User.Role.valueOf(request.role()));
        existingUser.setUsername(request.username());
        existingUser.setPassword(request.password());
        existingUser.setActive(request.active());
        existingUser.setUpdatedAt(java.time.LocalDateTime.now());
    }

    /**
     * Convierte una lista de entidades User a lista de UserDTO
     */
    public java.util.List<UserDTO> toDTOList(java.util.List<User> users) {
        if (users == null) {
            return java.util.Collections.emptyList();
        }

        return users.stream()
                .map(this::toDTO)
                .toList();
    }

    /**
     * Convierte una lista de entidades User a lista de UserSummaryDTO
     */
    public java.util.List<UserSummaryDTO> toSummaryDTOList(java.util.List<User> users) {
        if (users == null) {
            return java.util.Collections.emptyList();
        }

        return users.stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    /**
     * DTO completo de Usuario
     */
    public static class UserDTO {

        private Long id;
        private String documentNumber;
        private String fullName;
        private String email;
        private String phone;
        private LocalDate birthDate;
        private String address;
        private String role;
        private String username;
        private boolean active;
        private java.time.LocalDateTime createdAt;
        private java.time.LocalDateTime updatedAt;

        // Constructor privado para Builder
        private UserDTO() {
        }

        public static UserDTOBuilder builder() {
            return new UserDTOBuilder();
        }

        // Getters
        public Long getId() {
            return id;
        }

        public String getDocumentNumber() {
            return documentNumber;
        }

        public String getFullName() {
            return fullName;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public String getAddress() {
            return address;
        }

        public String getRole() {
            return role;
        }

        public String getUsername() {
            return username;
        }

        public boolean isActive() {
            return active;
        }

        public java.time.LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public java.time.LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        // Builder
        public static class UserDTOBuilder {

            private UserDTO dto = new UserDTO();

            public UserDTOBuilder id(Long id) {
                dto.id = id;
                return this;
            }

            public UserDTOBuilder documentNumber(String documentNumber) {
                dto.documentNumber = documentNumber;
                return this;
            }

            public UserDTOBuilder fullName(String fullName) {
                dto.fullName = fullName;
                return this;
            }

            public UserDTOBuilder email(String email) {
                dto.email = email;
                return this;
            }

            public UserDTOBuilder phone(String phone) {
                dto.phone = phone;
                return this;
            }

            public UserDTOBuilder birthDate(LocalDate birthDate) {
                dto.birthDate = birthDate;
                return this;
            }

            public UserDTOBuilder address(String address) {
                dto.address = address;
                return this;
            }

            public UserDTOBuilder role(String role) {
                dto.role = role;
                return this;
            }

            public UserDTOBuilder username(String username) {
                dto.username = username;
                return this;
            }

            public UserDTOBuilder active(boolean active) {
                dto.active = active;
                return this;
            }

            public UserDTOBuilder createdAt(java.time.LocalDateTime createdAt) {
                dto.createdAt = createdAt;
                return this;
            }

            public UserDTOBuilder updatedAt(java.time.LocalDateTime updatedAt) {
                dto.updatedAt = updatedAt;
                return this;
            }

            public UserDTO build() {
                return dto;
            }
        }
    }

    /**
     * DTO resumido de Usuario
     */
    public static class UserSummaryDTO {

        private Long id;
        private String documentNumber;
        private String fullName;
        private String email;
        private String role;
        private boolean active;

        private UserSummaryDTO() {
        }

        public static UserSummaryDTOBuilder builder() {
            return new UserSummaryDTOBuilder();
        }

        // Getters
        public Long getId() {
            return id;
        }

        public String getDocumentNumber() {
            return documentNumber;
        }

        public String getFullName() {
            return fullName;
        }

        public String getEmail() {
            return email;
        }

        public String getRole() {
            return role;
        }

        public boolean isActive() {
            return active;
        }

        // Builder
        public static class UserSummaryDTOBuilder {

            private UserSummaryDTO dto = new UserSummaryDTO();

            public UserSummaryDTOBuilder id(Long id) {
                dto.id = id;
                return this;
            }

            public UserSummaryDTOBuilder documentNumber(String documentNumber) {
                dto.documentNumber = documentNumber;
                return this;
            }

            public UserSummaryDTOBuilder fullName(String fullName) {
                dto.fullName = fullName;
                return this;
            }

            public UserSummaryDTOBuilder email(String email) {
                dto.email = email;
                return this;
            }

            public UserSummaryDTOBuilder role(String role) {
                dto.role = role;
                return this;
            }

            public UserSummaryDTOBuilder active(boolean active) {
                dto.active = active;
                return this;
            }

            public UserSummaryDTO build() {
                return dto;
            }
        }
    }

    /**
     * DTO de perfil de Usuario
     */
    public static class UserProfileDTO {

        private Long id;
        private String documentNumber;
        private String fullName;
        private String email;
        private String phone;
        private LocalDate birthDate;
        private String address;
        private String role;
        private String username;
        private boolean active;
        private java.time.LocalDateTime createdAt;
        private java.time.LocalDateTime updatedAt;

        private UserProfileDTO() {
        }

        public static UserProfileDTOBuilder builder() {
            return new UserProfileDTOBuilder();
        }

        // Getters
        public Long getId() {
            return id;
        }

        public String getDocumentNumber() {
            return documentNumber;
        }

        public String getFullName() {
            return fullName;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public String getAddress() {
            return address;
        }

        public String getRole() {
            return role;
        }

        public String getUsername() {
            return username;
        }

        public boolean isActive() {
            return active;
        }

        public java.time.LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public java.time.LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        // Builder
        public static class UserProfileDTOBuilder {

            private UserProfileDTO dto = new UserProfileDTO();

            public UserProfileDTOBuilder id(Long id) {
                dto.id = id;
                return this;
            }

            public UserProfileDTOBuilder documentNumber(String documentNumber) {
                dto.documentNumber = documentNumber;
                return this;
            }

            public UserProfileDTOBuilder fullName(String fullName) {
                dto.fullName = fullName;
                return this;
            }

            public UserProfileDTOBuilder email(String email) {
                dto.email = email;
                return this;
            }

            public UserProfileDTOBuilder phone(String phone) {
                dto.phone = phone;
                return this;
            }

            public UserProfileDTOBuilder birthDate(LocalDate birthDate) {
                dto.birthDate = birthDate;
                return this;
            }

            public UserProfileDTOBuilder address(String address) {
                dto.address = address;
                return this;
            }

            public UserProfileDTOBuilder role(String role) {
                dto.role = role;
                return this;
            }

            public UserProfileDTOBuilder username(String username) {
                dto.username = username;
                return this;
            }

            public UserProfileDTOBuilder active(boolean active) {
                dto.active = active;
                return this;
            }

            public UserProfileDTOBuilder createdAt(java.time.LocalDateTime createdAt) {
                dto.createdAt = createdAt;
                return this;
            }

            public UserProfileDTOBuilder updatedAt(java.time.LocalDateTime updatedAt) {
                dto.updatedAt = updatedAt;
                return this;
            }

            public UserProfileDTO build() {
                return dto;
            }
        }
    }
}
