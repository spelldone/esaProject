# Lab 1.
#### 6133 Новичков Егор, Бехтерев Алексей.
## Установка и запуск сервера glassfish
Скачиваем glassfish сервер 5ой версии.
В папке config редактируем файл asenv.bat и добавляем строчку
```
set AS_JAVA=C:\Users\spell\.jdks\corretto-1.8.0_312
```
Также скачиваем jdbc драйвер для PostgreSQL и кладем его в папку lib
Далее запускаем сервер из папки bin командой
```
asadmin start-domain 
```

## Конфигурация бд

![image](https://user-images.githubusercontent.com/61648756/212110799-b5153d03-6fe8-4bb2-a374-e3a9816d089b.png)

![image](https://user-images.githubusercontent.com/61648756/212111009-7641a27c-f110-4be7-a920-7191f44fda22.png)

![image](https://user-images.githubusercontent.com/61648756/212111144-5b4f506b-7283-4ecb-a911-d0189e9c1a61.png)

## Модель данных

![image](https://user-images.githubusercontent.com/61648756/212112263-b65269af-abb0-4af4-8662-e7cf1f2db91a.png)

Для каждой сущности были созданы классы описывающие их, а также использован инструмент hibernate'а NamedQueries
и созданы репозитории, которые наследуют TransactionBean'ы для работы с entityManager'ом, для работы с данными сущностями.

![image](https://user-images.githubusercontent.com/61648756/212119304-e72fc085-edf8-4ee9-a0db-a3b3b2dfebbf.png)

# Task 6

В качестве уровня view было реализовано несколько веб-сервлетов, которые предоставляют REST API для таблиц.
В сервлеты объекты dao вводятся с использованием аннотации внедрения зависимостей (@EJB)

### Примеры с использованием Postman:

Создаем продукт:

![image](https://user-images.githubusercontent.com/61648756/212124780-1ee57016-0644-4c14-991a-5bfed7884b37.png)

Получаем все продукты, видим что предыдущий запрос на создание сработал
![image](https://user-images.githubusercontent.com/61648756/212124854-01b030db-3cf1-456e-b3ae-58adb2500b09.png)

Удаляем созданный раннее продукт
![image](https://user-images.githubusercontent.com/61648756/212125495-365ba496-7be7-4a77-9041-0646a6cff363.png)

Проверяем

![image](https://user-images.githubusercontent.com/61648756/212125574-38adec08-f2c3-4b35-8e5b-ec552bbc4229.png)

