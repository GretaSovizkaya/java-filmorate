package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<Integer, Set<Integer>> friendships = new HashMap<>();
    private int idCounter = 1;


    @Override
    public User addUser(User user) {
        user.setId(idCounter++);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        users.remove(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUserById(int id) {
        return users.get(id);
    }

    @Override
    public void addFriend(int id, int friendId) {
        friendships.get(id).add(friendId);
        friendships.get(friendId).add(id);

    }

    @Override
    public void removeFriend(int id, int friendId) {
        friendships.get(id).remove(friendId);
        friendships.get(friendId).remove(id);
    }

    @Override
    public List<User> getFriends(int id) {
        Set<Integer> friendIds = friendships.get(id);
        List<User> friends = new ArrayList<>();
        for (int friendId : friendIds) {
            friends.add(users.get(friendId));
        }
        return friends;
    }

}
