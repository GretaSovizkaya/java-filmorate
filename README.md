# java-filmorate
![ ](https://github.com/GretaSovizkaya/java-filmorate/blob/3e69b1ee45ade0c837f666108c7d7b4c28a52c7b/ER%20Diagram%20Filmorate.png)
Примеры запросов для основных операций приложения:
1) Получение всех фильмов
   SELECT * FROM films;
   
2) Получение фильма по ID:
   SELECT * FROM films
   WHERE id = 1;
   
3) Добавление нового пользователя:
   INSERT INTO users (name, email, login, birthday)
   VALUES ('John Doe', 'johndoe@example.com', 'john123', '1990-01-01');

4) Получение списка друзей пользователя:
   SELECT u.*
   FROM users u
   JOIN friends f ON u.id = f.friend_id
   WHERE f.user_id = 1;
