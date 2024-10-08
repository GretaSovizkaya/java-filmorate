package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();
    User getUserById(int id);
    void addFriend(int id, int friendId);
    void removeFriend(int id, int friendId);
    List<User> getFriends(int id);
}
