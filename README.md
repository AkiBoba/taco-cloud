Создание приложения Taco Cloud по книге 
1. Инициализация проекта Spring
2. Первой функцией в приложении Taco Cloud – станет домашняя
   страница. Начнем с класса контроллера.
3. Создадим страницу выбора ингридиентов для такос
4. Создали страницу оформления заказа
5. Создали контроллер приемки данных заказа
   1. Проверка данных в форме
   Чтобы организовать проверку в Spring MVC, необходимо:
      добавить в сборку начальную зависимость от Spring Validation;
      объявить правила проверки для проверяемого класса: в частности, для класса Taco;
      указать, в каких методах каких контроллеров должна выполняться проверка: в данном случае в методе processTaco() контроллера DesignTacoController и в методе processOrder() контроллера
      OrderController;
   изменить представления форм для отображения выявленных ошибок
6. Добавление Spring Data JDBC в спецификацию сборки
7. Настройка JDBC и подключение БД PostgreSQL
8. Включение Spring Security. Проверка регистрации, регистрация юзера.
9. Добавление таблицы user_order, отключение csrf, подключение записи логов в файл
10. Работа с конфигурацией