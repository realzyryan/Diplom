## Описание приложения

Приложение представляет из себя веб-сервис.
![pic/service.png](https://github.com/netology-code/qa-diploma/blob/master/pic/service.png)
Приложение предлагает купить тур по определённой цене с помощью двух способов:

1. Обычная оплата по дебетовой карте
2. Уникальная технология: выдача кредита по данным банковской карты

## Документы:

- План автоматизации ([Plan.md](https://github.com/realzyryan/Diplom/blob/main/Plan.md))
- Отчёт о проведённом тестировании ([Report.md](https://github.com/realzyryan/Diplom/blob/main/Report.md))
- Отчёт о проведённой автоматизации ([Summary.md](https://github.com/realzyryan/Diplom/blob/main/Summary.md))

## Предустановленное ПО:

- Intellij idea Ultimate Edition
- Docker Desktop
- Node.js
- Git
- Google Chrome

## Параметры окружения
- java 17
- JAVA_HOME v17
- node.js v20.17.0
- npm v10.8.3

## Процедура запуска авто-тестов:

1. Запустить Docker Desktop
2. Для копирования репозитория в терминале выполнить команду `git clone <ссылка на репозиторий>`
3. Запускаем контейнеры с помощью команды в терминале `docker-compose up`. Должно запуститься сразу 3 контейнера.
4. В терминале выполняем команду `java -jar .\artifacts\aqa-shop.jar`. Приложение открывается на
   странице http://localhost:8080/
5. Запускаем автотесты: `./gradlew clean test`
6. Генерируем отчёт по итогам тестирования с помощью Allure командой: `./gradlew allureServe`
7. Отчет автоматически откроется в браузере.

